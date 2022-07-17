package com.chen.controller;

import com.chen.dao.*;
import com.chen.entity.*;
import com.chen.monitor_data.Aqi_calculate;
import com.chen.monitor_data.TimeSimple;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class Display_Ctrl {

    @Resource
    Pollution_Dao pollution_dao;

    @Resource
    Analysis_Dao analysis_dao;

    @Resource
    Exception_message_Dao exc_dao;

    @Resource
    Log_Dao log_dao;

    //空气质量显示
    @RequestMapping("/airquality")
    public String airQuality(Model mv,String city){
        Analysis aysis = null;
        String data_maxname = null;
        System.out.println("city="+city);
            if (!"".equals(city)) {
                aysis = analysis_dao.select_cityAqi(city);
                data_maxname = max_pollution(aysis);
            }
            mv.addAttribute("city_aqi", aysis);
            mv.addAttribute("data_maxname", data_maxname);
        return "airquality";
    }

    //*实时污染物数据表格显示
    @RequestMapping("/realdata")
    @ResponseBody
    public List<Pollution> realdata(String city){
//        System.out.println(city);
        List<Pollution> list = null;
        if(!"".equals(city)){
            list = pollution_dao.select_city(city);
            for(Pollution p:list){
                System.out.println(p);
            }
        }else {
            System.out.println("没有选择城市");
        }
        return list;
    }

    //*实时污染物图表显示
    @RequestMapping("/realdata_charts")
    @ResponseBody
    public Map realdata_charts(String city){
        System.out.println("city="+city);
        List<Pollution> list = null;
        Map map = new HashMap();
        if(!"".equals(city)){
            list = pollution_dao.select_city02(city);
            if(list.size()!=0){
                String monitor_point = list.get(0).getMonitor_point();
                String date = list.get(0).getDate().substring(0,10);
                String []hm = new String[list.size()];
                double []so2 = new double[list.size()];
                double []no2 = new double[list.size()];
                double []co = new double[list.size()];
                double []pm2_5 = new double[list.size()];
                double []pm10 = new double[list.size()];
                double []o3 = new double[list.size()];
                int index = 0;
                for(Pollution p:list){
                    so2[index] = p.getSO2();
                    no2[index] = p.getNO2();
                    o3[index] = p.getO3();
                    co[index] = p.getCO();
                    pm2_5[index] = p.getPM2_5();
                    pm10[index] = p.getPM10();
                    hm[index] = p.getDate().substring(11,16);
                    index++;
                }
            /*System.out.println(so2.toString());
            System.out.println(no2.toString());
            System.out.println(co.toString());
            System.out.println(pm2_5.toString());
            System.out.println(pm10.toString());
            System.out.println(o3.toString());
            System.out.println(hm.toString());*/
                map.put("so2arr",so2);
                map.put("no2arr",no2);
                map.put("coarr",co);
                map.put("o3arr",o3);
                map.put("pm2_5arr",pm2_5);
                map.put("pm10arr",pm10);
                map.put("hm",hm);
                map.put("monitor_point",monitor_point);
                map.put("date",date);
            }

        }else {
            System.out.println("没有选择城市");
        }
        return map;
    }

    //*历史空气质量数据图表显示
    @RequestMapping("/data_agocharts")
    @ResponseBody
    public Map show(String form_city, String form_date){
        System.out.println("form_date="+form_date);
        System.out.println("form_city="+form_city);
        List<Analysis> analyses_List = null;
        Map map = new HashMap();
        String date = form_date;
        String city = form_city;
        String monitor_point = "";
        if("".equals(form_city)){
            System.out.println("11111111111");
            analyses_List = analysis_dao.select_Aqi("江门", TimeSimple.timeSimple_yMd());
            city = "江门";
            monitor_point = "蓬江区";
            date = TimeSimple.timeSimple_yMd();
            System.out.println("显示默认的数据");
        }else {
            System.out.println("2222222222222");
            analyses_List = analysis_dao.select_Aqi(form_city,form_date);
        }
        if(!analyses_List.isEmpty()){
            System.out.println("指标查询");
            System.out.println(analyses_List.toString());
            Double []aqis_arr = new Double[analyses_List.size()];
            String []hours_arr = new String[analyses_List.size()];

            for(int i=0 ;i<analyses_List.size();i++){
                hours_arr[i] =  analyses_List.get(i).getDate().substring(11,16);
                aqis_arr[i] = analyses_List.get(i).getAqi();
            }
            city = analyses_List.get(0).getCity();
            monitor_point = analyses_List.get(0).getMonitor_point();
            map.put("hours",hours_arr);
            map.put("aqis",aqis_arr);
        }
        map.put("city",city);
        map.put("monitor_point",monitor_point);
        map.put("date",date);
        return map;
    }

    //*历史空气质量数据表格显示
    @RequestMapping("/data_ago")
    @ResponseBody
    public List<Analysis> data_ago(String city,String form_date){
        System.out.println("city="+city);
        System.out.println("form_date="+form_date);
        List<Analysis> analyses_List = null;
        if("".equals(city)){
            analyses_List = analysis_dao.select_Aqi("江门", TimeSimple.timeSimple_yMd());
            System.out.println("默认江门今天数据");
        }else {
            analyses_List = analysis_dao.select_Aqi(city, form_date);
            System.out.println("指定查询");
        }
        return analyses_List;
    }

    //*查看污染物异常数据
    @RequestMapping("/data_exception")
    @ResponseBody
    public Map data_exception(Integer pageNum, Integer pageSize){
        System.out.println("pageNum="+pageNum+" pageSize="+pageSize);
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        List<Exception_message> list = exc_dao.select_ALl();
        map.put("list",list);
        map.put("Allsize",exc_dao.select_ALl().size());
        return map;
    }

    //相关知识了解
    @RequestMapping("/knowledge_show")
    public String knowledge_show(){

        return "knowledge_show";
    }

    //历史数据范围查询
    @RequestMapping("/rangequery")
    @ResponseBody
    public List<Analysis> rangequery(String city,String form_date,Integer first,Integer end){
        System.out.println("city="+city);
        System.out.println("form_date="+form_date);
        System.out.println("fisrt="+first+" end="+end);
        List<Analysis> analyses_List = null;

            analyses_List = analysis_dao.select_rangeAqi(city, form_date,first,end);

        return analyses_List;
    }

    //历史数据某天显示最大值
    @RequestMapping("/maxquery")
    @ResponseBody
    public Analysis maxquery(String city,String form_date){
        System.out.println("city="+city);
        System.out.println("form_date="+form_date);
        return analysis_dao.select_maxAqi(city, form_date);
    }

    //查询历史污染物数据
    @RequestMapping("/show_polu")
    @ResponseBody
    public Map show_polu(String city,String query_date,Integer pageNum, Integer pageSize){
        System.out.println("city="+city+" "+"query_date="+query_date);
        System.out.println("pagenum= "+pageNum+" "+"pageSize= "+pageSize);
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        List<Pollution> list = pollution_dao.select_cityAndDate(city,query_date);
        map.put("list",list);
        map.put("Allsize",pollution_dao.select_cityAndDate(city,query_date).size());
        return map;
    }

    //修改污染物数据
    @RequestMapping("/update_polu")
    @ResponseBody
    public String update_polu(String date, String type, Double value, String update_city, HttpSession session){
        System.out.println("date="+date+" type="+type+" value="+value+" city="+update_city);
        int i = pollution_dao.update_data(update_city,date,type,value);
        int j = pollution_dao.update_data02(update_city,date,type,value);
        System.out.println("ipolu="+i+" j="+j);
        if(i==1){
            String message = "修改了一次污染物数据:"+update_city+"的"+date+" "+type+"值修改为"+value;
            log_dao.insert_log(new Log(TimeSimple.timeSimple(),(String)session.getAttribute("username"),message));
            //修改analysis的表格
            String date02 = date.substring(0,13); //精确到小时
            Double polu_mean = pollution_dao.select_polu_mean(update_city,date02,type);
            System.out.println("date02="+date02);
            System.out.println("polumean="+polu_mean);
            double polu_Aqi = 0;
            String ana_type ="";
            switch (type){
                case "SO2"   : polu_Aqi= Aqi_calculate.getSO2(polu_mean);  ana_type="SO2_Mean";  System.out.println("是"+type+"类型"+" polu_Aqi="+polu_Aqi+" ana_type="+ana_type);break;
                case "NO2"   : polu_Aqi= Aqi_calculate.getNO2(polu_mean);  ana_type="NO2_Mean"  ;System.out.println("是"+type+"类型"+" polu_Aqi="+polu_Aqi+" ana_type="+ana_type);break;
                case "O3"    : polu_Aqi= Aqi_calculate.getO3(polu_mean);   ana_type="O3_Mean"  ; System.out.println("是"+type+"类型"+" polu_Aqi="+polu_Aqi+" ana_type="+ana_type);break;
                case "CO"    : polu_Aqi= Aqi_calculate.getCO(polu_mean);   ana_type="CO_Mean"  ; System.out.println("是"+type+"类型"+" polu_Aqi="+polu_Aqi+" ana_type="+ana_type);break;
                case "PM2_5" : polu_Aqi= Aqi_calculate.getPM2_5(polu_mean);ana_type="PM2_5_Mean";System.out.println("是"+type+"类型"+" polu_Aqi="+polu_Aqi+" ana_type="+ana_type);break;
                case "PM10"  : polu_Aqi= Aqi_calculate.getPM10(polu_mean); ana_type="PM10_Mean"; System.out.println("是"+type+"类型"+" polu_Aqi="+polu_Aqi+" ana_type="+ana_type);break;
            }
            Double h_aqi = analysis_dao.select_cityAndDate(update_city,date02);
            System.out.println("h_aqi="+h_aqi);
            int i_aqi=0;
            if(h_aqi!=null){
                if(polu_Aqi > h_aqi){
                    i_aqi = analysis_dao.update_newAqi(update_city,date02,ana_type,polu_Aqi,polu_mean);
                    analysis_dao.update_newAqi02(update_city,date02,ana_type,polu_Aqi,polu_mean);
                }else {
                    i_aqi = analysis_dao.update_newAqi(update_city,date02,ana_type,h_aqi,polu_mean);
                    analysis_dao.update_newAqi02(update_city,date02,ana_type,h_aqi,polu_mean);
                }
            }else {
                System.out.println("该小时没有计算空气质量情况");
            }
            System.out.println("i_aqi="+i_aqi);
            return "修改成功，请重新查询";
        }else {
            System.out.println("该时间没有污染物数据");
            return "该时间没有污染物数据";
        }

    }

    //返回最大污染物的名字
    public String max_pollution(Analysis aysis){
        class Polu{
            String name;
            Double data;
            Polu(String name,Double data){
                this.name = name;
                this.data = data;
            }
        }
        TreeSet<Polu> set = new TreeSet<Polu>(new Comparator<Polu>() {
            @Override
            public int compare(Polu o1, Polu o2) {
                return o1.data>o2.data?1:-1;
            }
        });
        set.add(new Polu("so2",Aqi_calculate.getSO2(aysis.getSO2_Mean())));
        set.add(new Polu("no2",Aqi_calculate.getNO2(aysis.getNO2_Mean())));
        set.add(new Polu("o3",Aqi_calculate.getO3(aysis.getO3_Mean())));
        set.add(new Polu("pm2_5",Aqi_calculate.getPM2_5(aysis.getPM2_5_Mean())));
        set.add(new Polu("pm10",Aqi_calculate.getPM10(aysis.getPM10_Mean())));
        set.add(new Polu("co",Aqi_calculate.getCO(aysis.getCO_Mean())));
        Polu a = set.last();
        System.out.println("-----------------------------"+a.name+"  aqi="+a.data);
        if("pm2_5".equals(a.name)){
            return "pm2.5";
        }
        return a.name;
    }


}
