package com.chen.dao;

import com.chen.entity.Analysis;
import com.chen.entity.Pollution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Pollution_Dao {

    //查看全部的污染物
    Pollution[] select_Pollution();

    //插入临时表污染物的数据
    int insert_Pollution(Pollution pollution);

    //插入长期表污染物的数据
    int insert_Pollution_long(Pollution pollution);

    //从污染物表查出平均值，用于插入analysis表的数据(这个一次会查询全部的，用来一次计算全部的aqi，测试系统用的)
    List<Analysis> select_Mean();

    //一次只查询某个小时内的所有数据
    List<Analysis> select_PoluMean(@Param("date")String date);

    //导出某一天的excel表格
    void output_Excel(@Param("startdate") String startdate,@Param("enddate") String enddate,@Param("city") String city);

    //根据城市来查引入的污染物的实时数据
    List<Pollution> select_city(String city);
    List<Pollution> select_city02(String city);

    //查询某一天污染物的数据
    List<Pollution> select_cityAndDate(@Param("city") String city,@Param("date") String date);

    //修改污染物数据
    int update_data(@Param("city")String city,@Param("date") String date,@Param("type")String type,@Param("value")Double value);
    int update_data02(@Param("city")String city,@Param("date") String date,@Param("type")String type,@Param("value")Double value);

    //用于计算修改污染物数据后，更正analysis表的
    Double select_polu_mean(@Param("city")String city,@Param("date")String date,@Param("type")String type);

    //删除污染物临时表的数据（超过10天的）
    int delete_polu(@Param("date")String date);



}
