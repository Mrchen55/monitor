package com.chen.entity;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class Pollution {

    private Integer id;
    private String date;
    private String city;
    private String monitor_point;
    private double SO2;
    private double NO2;
    private double PM10;
    private double PM2_5;
    private double O3;
    private double CO;
    private String hour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
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

    public double getSO2() {
        return SO2;
    }

    public void setSO2(double SO2) {
        this.SO2 = SO2;
    }

    public double getNO2() {
        return NO2;
    }

    public void setNO2(double NO2) {
        this.NO2 = NO2;
    }

    public double getPM10() {
        return PM10;
    }

    public void setPM10(double PM10) {
        this.PM10 = PM10;
    }

    public double getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(double PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public double getO3() {
        return O3;
    }

    public void setO3(double o3) {
        O3 = o3;
    }

    public double getCO() {
        return CO;
    }

    public void setCO(double CO) {
        this.CO = CO;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Pollution{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", monitor_point='" + monitor_point + '\'' +
                ", SO2=" + SO2 +
                ", NO2=" + NO2 +
                ", PM10=" + PM10 +
                ", PM2_5=" + PM2_5 +
                ", O3=" + O3 +
                ", CO=" + CO +
                ", hour=" + hour +
                '}';
    }
}
