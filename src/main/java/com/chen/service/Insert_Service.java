package com.chen.service;

import com.chen.entity.Analysis;
import com.chen.entity.Pollution;

import java.util.List;

public interface Insert_Service {

    //用于插入每个小时的不同城市的aqi
    void insert_Analysis(String date);

}
