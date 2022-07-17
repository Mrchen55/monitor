package com.chen.dao;

import com.chen.entity.User;
import org.apache.ibatis.annotations.Param;

public interface User_Dao {

    int insert_User(User user);
    User select_User(@Param("phone") Long phone,@Param("name") String name,@Param("email")String email);
    //修改用户信息
    int update_User(@Param("username")String username,@Param("password")String password,@Param("phone")Long phone,@Param("email")String email);

    //找回密码
    User back_psd(@Param("phone")Long phone,@Param("email")String email);
}
