package com.chen.dao;

import com.chen.entity.Exception_message;

import java.util.List;

public interface Exception_message_Dao {

    int insert_message(Exception_message exception_message);

    List<Exception_message> select_ALl();

}
