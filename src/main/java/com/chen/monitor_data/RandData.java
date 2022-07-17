package com.chen.monitor_data;

import com.chen.entity.Pollution;
import com.chen.service.MailService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandData {

/*
PM2.5：  5-100        μg的
PM10：   10-150
CO：     0.2-1.5     mg的
NO2：    5-120
O3:      10-250
SO2:     5-50      */
    //用一个随机数来模拟出污染物的数据

    public static Pollution getPollution(){
        Pollution pollution = new Pollution();
        double pm2_5 = rand_Data(5,100) ;
        double pm10  = rand_Data(10,150);
        double co = rand_Data(20,150)/100.00;
        double no2 = rand_Data(5,120);
        double o3 = rand_Data(10,250);
        double so2 = rand_Data(5,50);

        pollution.setPM2_5(pm2_5);
        pollution.setPM10(pm10);
        pollution.setCO(co);
        pollution.setNO2(no2);
        pollution.setO3(o3);
        pollution.setSO2(so2);
        pollution.setHour(TimeSimple.time_hour());

        return pollution;
    }

    //根据给定的范围值来随机出一个合理的数据
    public static int rand_Data(int min,int max){
        Random random = new Random();
        return random.nextInt(max-min+1)+min;
    }

    //用于判断随机出的数值是否超标,false不超标，true就是超标.
    public static Map judge(Pollution pollution){
        Map map = new HashMap();
        map.put("flag",false);
        if(pollution.getPM2_5()>98){
            map.put("flag",true);
            map.put("type","PM2.5");
            map.put("value",pollution.getPM2_5());
        }
        if(pollution.getPM10()>148){
            map.put("flag",true);
            map.put("type","PM10");
            map.put("value",pollution.getPM10());
        }
        if(pollution.getCO()>1.48){
            map.put("flag",true);
            map.put("type","CO");
            map.put("value",pollution.getCO());
        }
        if(pollution.getNO2()>118){
            map.put("flag",true);
            map.put("type","NO2");
            map.put("value",pollution.getNO2());
        }
        if(pollution.getO3()>248){
            map.put("flag",true);
            map.put("type","O3");
            map.put("value",pollution.getO3());
        }
        if(pollution.getSO2()>48){
            map.put("flag",true);
            map.put("type","SO2");
            map.put("value",pollution.getSO2());
        }
        return map;
    }

    /*public static void main(String[] args) {
        Pollution pollution = getPollution();
        Map map = judge(pollution);
        Set<Map.Entry<String,Object>> set = map.entrySet();
        for(Map.Entry<String,Object> node : set){
            System.out.println(node.getKey() + "--->" + node.getValue());
        }
        if((Boolean)map.get("flag")){
            System.out.println("11111="+map.get("type"));
        };

        System.out.println(pollution);
    }*/
}
