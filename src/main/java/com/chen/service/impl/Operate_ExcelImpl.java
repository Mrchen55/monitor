package com.chen.service.impl;

import com.chen.dao.Pollution_Dao;
import com.chen.monitor_data.TimeSimple;
import com.chen.service.Operate_Excel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("operate_ExcelImpl")
public class Operate_ExcelImpl implements Operate_Excel{


    @Resource
    Pollution_Dao pollution_dao;

    @Override
    public void export_Excel(String city) {
        String start = TimeSimple.timeSimple_yMd_02(7);
        String end = TimeSimple.timeSimple_yMd_02(1);

        pollution_dao.output_Excel(start,end,city);
    }


}
