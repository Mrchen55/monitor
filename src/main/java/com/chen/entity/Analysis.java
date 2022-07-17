package com.chen.entity;

import java.util.Date;

public class Analysis {

    private Integer id;
    private String date;
    private String city;
    private String monitor_point;
    private double Aqi;
    private double SO2_Mean;
    private double NO2_Mean;
    private double PM10_Mean;
    private double PM2_5_Mean;
    private double O3_Mean;
    private double CO_Mean;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public double getSO2_Mean() {
        return SO2_Mean;
    }

    public void setSO2_Mean(double SO2_Mean) {
        this.SO2_Mean = SO2_Mean;
    }

    public double getNO2_Mean() {
        return NO2_Mean;
    }

    public void setNO2_Mean(double NO2_Mean) {
        this.NO2_Mean = NO2_Mean;
    }

    public double getPM10_Mean() {
        return PM10_Mean;
    }

    public void setPM10_Mean(double PM10_Mean) {
        this.PM10_Mean = PM10_Mean;
    }

    public double getPM2_5_Mean() {
        return PM2_5_Mean;
    }

    public void setPM2_5_Mean(double PM2_5_Mean) {
        this.PM2_5_Mean = PM2_5_Mean;
    }

    public double getO3_Mean() {
        return O3_Mean;
    }

    public void setO3_Mean(double o3_Mean) {
        O3_Mean = o3_Mean;
    }

    public double getCO_Mean() {
        return CO_Mean;
    }

    public void setCO_Mean(double CO_Mean) {
        this.CO_Mean = CO_Mean;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMonitor_point() {
        return monitor_point;
    }

    public void setMonitor_point(String monitor_point) {
        this.monitor_point = monitor_point;
    }

    public double getAqi() {
        return Aqi;
    }

    public void setAqi(double aqi) {
        Aqi = aqi;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", monitor_point='" + monitor_point + '\'' +
                ", Aqi=" + Aqi +
                ", SO2_Mean=" + SO2_Mean +
                ", NO2_Mean=" + NO2_Mean +
                ", PM10_Mean=" + PM10_Mean +
                ", PM2_5_Mean=" + PM2_5_Mean +
                ", O3_Mean=" + O3_Mean +
                ", CO_Mean=" + CO_Mean +
                '}';
    }
}
