package com.chen.monitor_data;

import com.chen.dao.Analysis_Dao;
import com.chen.dao.Data_Log_Dao;
import com.chen.dao.Exception_message_Dao;
import com.chen.dao.Pollution_Dao;
import com.chen.entity.Data_Log;
import com.chen.entity.Exception_message;
import com.chen.entity.Pollution;
import com.chen.service.MailService;
import com.chen.service.Operate_Excel;
import com.chen.service.impl.Insert_ServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.awt.*;
import java.util.Map;

/**
 * 定时插入随机污染物数据
 * 计算aqi
 */
@Component
@EnableScheduling
@Async("Scheduled_Executor")
public class Monitor_Scheduled {

    @Resource(name = "insert_ServiceImpl")
    Insert_ServiceImpl insert_service;

    @Resource
    Pollution_Dao pollution_dao;

    @Resource
    Operate_Excel operate_excel;

    @Resource
    Data_Log_Dao data_log_dao;

    @Resource
    Analysis_Dao analysis_dao;

    @Resource
    MailService mailService;

    @Resource
    Exception_message_Dao exception_dao;

    //插入江门的(5分钟一次),从启动项目开始就开始录入数据。
    @Scheduled(fixedRate = 60000*5)
    public void randJM(){
        local_inspolu("江门","蓬江区");

    }

    //插入广州的
    @Scheduled(fixedRate = 60000*5)
    public void randGZ(){
        local_inspolu("广州","白云区");
    }

    //插入珠海的
    @Scheduled(fixedRate = 60000*5)
    public void randZH(){
        local_inspolu("珠海","金湾区");
    }

    //插入佛山的
    @Scheduled(fixedRate = 60000*5)
    public void randFS(){
        local_inspolu("佛山","大沥区");
    }

    //插入Analysis表的数据(每天的0时开始，然后隔一小时执行一次)
    @Scheduled(cron = "0 0 */1 * * *")
    public void analysis(){
        insert_service.insert_Analysis(TimeSimple.timeSimple_ymd_03());
        System.out.println(TimeSimple.timeSimple()+":计算了一次analysis数据");
        data_log_dao.insert_message(new Data_Log(TimeSimple.timeSimple(),"","","插入了一次analysis表格数据"));
    }

    //定时清理污染物临时表的数据（清除超过10天的）
    @Scheduled(cron = "0 0 15 * * *")
    public void del_polu(){
        int i = pollution_dao.delete_polu(TimeSimple.timeSimple_yMd_02(9));
        System.out.println(TimeSimple.timeSimple()+":清除了一次污染物临时表的超过10天的数据,共"+i+"条数据");
        data_log_dao.insert_message(new Data_Log(TimeSimple.timeSimple(),"","","清除了一次污染物临时表的超过10天数据,共"+i+"条数据"));
    }

    //定时清理analysis临时表的数据（清除超过30天的）
    @Scheduled(cron = "0 0 15 * * *")
    public void del_analysis(){
        int i = analysis_dao.delete_analysis(TimeSimple.timeSimple_yMd_02(29));
        System.out.println(TimeSimple.timeSimple()+":清除了一次analysis临时表的超过10天的数据,共"+i+"条数据");
        data_log_dao.insert_message(new Data_Log(TimeSimple.timeSimple(),"","","清除了一次analysis临时表的超过30天数据,共"+i+"条数据"));
    }

    //定时导出表格(每周一导出数据一次,只导今天日期的上7天的)
    @Scheduled(cron = "0 0 15 * * Mon")
    public void out_excel(){
        operate_excel.export_Excel("江门");
        operate_excel.export_Excel("广州");
        operate_excel.export_Excel("佛山");
        operate_excel.export_Excel("珠海");
        System.out.println(TimeSimple.timeSimple()+":导出了一次表格数据");
        data_log_dao.insert_message(new Data_Log(TimeSimple.timeSimple(),"","","导出了一次污染物数据excel表"));
    }

    //插入污染物数据的函数
    public void local_inspolu(String city,String monitor){
        Pollution pollution = RandData.getPollution();
        Map map = RandData.judge(pollution);
        if((Boolean)map.get("flag")){
            Exception_message exp = new Exception_message(TimeSimple.timeSimple(),city,monitor,"监控到的"+map.get("type")+"数据出现了异常,值为"+map.get("value"));
            exception_dao.insert_message(exp);
            String content = TimeSimple.timeSimple()+"\n"+city+monitor+"监控到的"+map.get("type")+"数据出现了异常,值为:"+map.get("value")+",请前往网站上查看";
            mailService.sendSimpleMail("1064726529@qq.com","数据异常通知",content);
        }
        pollution.setDate(TimeSimple.timeSimple());
        pollution.setCity(city);
        pollution.setMonitor_point(monitor);
        int i = pollution_dao.insert_Pollution(pollution);
        int i2 = pollution_dao.insert_Pollution_long(pollution);
        System.out.println("插入pollution="+i+" i2="+i2);
        System.out.println(TimeSimple.timeSimple()+":"+city+monitor+"插入了一次"+"数据");
        data_log_dao.insert_message(new Data_Log(TimeSimple.timeSimple(),city,monitor,city+monitor+"插入了一次"+"数据"));

    }


}
