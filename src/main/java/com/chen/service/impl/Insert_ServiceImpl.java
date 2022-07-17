package com.chen.service.impl;

import com.chen.dao.Analysis_Dao;
import com.chen.dao.Pollution_Dao;
import com.chen.entity.Analysis;
import com.chen.entity.Pollution;
import com.chen.monitor_data.Aqi_calculate;
import com.chen.monitor_data.RandData;
import com.chen.monitor_data.TimeSimple;
import com.chen.service.Insert_Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "insert_ServiceImpl")
public class Insert_ServiceImpl implements Insert_Service {

    @Resource
    Pollution_Dao pollution_dao;

    @Resource
    Analysis_Dao analysis_dao;

    //用于插入某个城市的一个小时的aqi
    @Override
    public void insert_Analysis(String date) {
        double[] pollution_data =new double[6];
        List<Analysis> list = pollution_dao.select_PoluMean(date);
        double aqi = 0;
        int i=0,i2=0;
        for(Analysis analysis:list){
            pollution_data[0]=analysis.getSO2_Mean();
            pollution_data[1]=analysis.getNO2_Mean();
            pollution_data[2]=analysis.getPM10_Mean();
            pollution_data[3]=analysis.getPM2_5_Mean();
            pollution_data[4]=analysis.getO3_Mean();
            pollution_data[5]=analysis.getCO_Mean();
            //算出某个城市一个小时的aqi
            aqi = Aqi_calculate.calculate(pollution_data);
//            System.out.println("max_aqi="+aqi);
            analysis.setAqi(aqi);
            analysis.setDate(analysis.getDate().substring(0,13));
//            System.out.println(analysis);
            i=analysis_dao.insert_Analysis(analysis);
            i2=analysis_dao.insert_Analysis_long(analysis);
        }
        System.out.println("插入analysis= "+i+" i2="+i2);
    }

}
