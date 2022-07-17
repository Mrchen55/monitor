package com.chen.dao;

import com.chen.entity.Analysis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Analysis_Dao {

    //用于插入analysis临时表格的数据的
    int insert_Analysis(Analysis analysis);

    //用于插入analysis长期表格的数据的
    int insert_Analysis_long(Analysis analysis);

    //用于查询某一天的数据的页面显示
    List<Analysis> select_Aqi(@Param("city") String city,@Param("date") String date);

    //查询某个城市的最近一小时的aqi值
    Analysis select_cityAqi(String city);

    //范围aqi查询
    List<Analysis> select_rangeAqi(@Param("city") String city,@Param("date") String date,@Param("first") Integer first,@Param("end") Integer end);

    //查询某天最大aqi
    Analysis select_maxAqi(@Param("city") String city,@Param("date") String date);

    //用于管理员修改表格数据的查询，查询某个污染物的某个时间的aqi
    Double select_cityAndDate(@Param("city")String city,@Param("date")String date);

    //用于管理员修改表格数据后，更新aqi的最新值
    int update_newAqi(@Param("city")String city,@Param("date") String date,@Param("type")String type,@Param("aqi")Double aqi,@Param("polumean")Double polumean);
    int update_newAqi02(@Param("city")String city,@Param("date") String date,@Param("type")String type,@Param("aqi")Double aqi,@Param("polumean")Double polumean);


    //删除污染物临时表的数据（超过10天的）
    int delete_analysis(@Param("date")String date);

}
