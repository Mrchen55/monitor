package com.chen.monitor_data;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aqi_calculate {

    /*计算某种污染物的aqi
    数组头部：IAQI,SO2,NO2,PM10,PM2.5,CO,O3*/
    private static double count_aqi(double C_p,int type){

        int[][] exponent_arr = {{0,0,0,0,0,0,0},
                                 {50,150,100,50,35,5,160},
                                 {100,500,200,150,75,10,200},
                                 {150,650,700,250,115,35,300},
                                 {200,800,1200,350,150,60,400},
                                 {300,0,2340,420,250,90,800},
                                 {400,0,3090,500,350,120,1000},
                                 {500,0,3840,600,500,150,1200}};
        double IAQI_p;
        double BPHi_p;
        double BPLo_p;
        double IAQIHi_p;
        double IAQLo_p;
        int degree = 0;
        if(C_p<exponent_arr[1][type]){
            degree = 1;
        }else if(C_p<exponent_arr[2][type]){
            degree = 2;
        }else if(C_p<exponent_arr[3][type]){
            degree = 3;
        }else if(C_p<exponent_arr[4][type]){
            degree = 4;
        }else if(C_p<exponent_arr[5][type]){
            degree = 5;
        }else if(C_p<exponent_arr[6][type]){
            degree = 6;
        }else if(C_p<exponent_arr[7][type]){
            degree = 7;
        }else {
            degree = 7;
        }
        BPHi_p = exponent_arr[degree][type];
        BPLo_p = exponent_arr[degree-1][type];
        IAQIHi_p = exponent_arr[degree][0];
        IAQLo_p = exponent_arr[degree-1][0];
        //计算这个种类的aqi
        IAQI_p = ((IAQIHi_p-IAQLo_p)/(BPHi_p-BPLo_p))*(C_p-BPLo_p)+IAQLo_p;
        return IAQI_p;
    }
    //得到SO2的每小时aqi
    public static double getSO2(double C_p){
//        System.out.println("so2="+count_aqi(C_p,1));
        return count_aqi(C_p,1);
    }
    //得到NO2的每小时aqi
    public static double getNO2(double C_p){
//        System.out.println("no2="+count_aqi(C_p,2));
        return count_aqi(C_p,2);
    }
    //得到PM10的每小时aqi
    public static double getPM10(double C_p){
//        System.out.println("pm10="+count_aqi(C_p,3));
        return count_aqi(C_p,3);
    }
    //得到PM2.5的每小时aqi
    public static double getPM2_5(double C_p){
//        System.out.println("pm2.5="+count_aqi(C_p,4));
        return count_aqi(C_p,4);
    }
    //得到CO的每小时aqi
    public static double getCO(double C_p){
//        System.out.println("co="+count_aqi(C_p,5));
        return count_aqi(C_p,5);
    }
    //得到O3的每小时aqi
    public static double getO3(double C_p){
//        System.out.println("o3="+count_aqi(C_p,6));
        return count_aqi(C_p,6);
    }
    //获取最大的aqi
    public static double calculate(double []pollutions){
        List<Double> list = new ArrayList<>();
        list.add(getSO2(pollutions[0]));
        list.add(getNO2(pollutions[1]));
        list.add(getPM10(pollutions[2]));
        list.add(getPM2_5(pollutions[3]));
        list.add(getO3(pollutions[4]));
        list.add(getCO(pollutions[5]));
        Collections.sort(list);
        return list.get(list.size()-1);
    }

}
