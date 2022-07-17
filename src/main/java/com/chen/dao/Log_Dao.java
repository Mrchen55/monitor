package com.chen.dao;

import com.chen.entity.Log;

import java.util.List;

public interface Log_Dao {

    int insert_log(Log log);

    List<Log> select_All();

}
