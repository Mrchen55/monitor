package com.chen.dao;

import com.chen.entity.Data_Log;
import org.apache.ibatis.annotations.Param;

public interface Data_Log_Dao {

    int insert_message(Data_Log data_log);

}
