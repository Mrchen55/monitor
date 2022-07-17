package com.chen.monitor_data;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeSimple {
    public static String timeSimple(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
    public static String time_hour(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-MM-dd HH");
        return simpleDateFormat.format(new Date());
    }
    public static String timeSimple_yMd(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-MM-dd");
        return simpleDateFormat.format(new Date());
    }
    //用于导出表格的,格式20220430，也用于删除临时表的
    public static String timeSimple_yMd_02(int i){
        Date dNow = new Date();   //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance();   //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -i);
        dBefore = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式

        return sdf.format(dBefore);
    }
    //用于aqi计算获取上一个小时的时间格式
    public static String timeSimple_ymd_03(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-MM-dd");
        SimpleDateFormat simpleDateFormat02 = new SimpleDateFormat("HH");
        String s1 = simpleDateFormat.format(new Date());
        return s1+" "+(Integer.parseInt(simpleDateFormat02.format(new Date()))-1);
    }

    public static void main(String[] args) {
        /* class Test_Stock {
             int goosId;
             String name;
             int number;
             Test_Stock(int i,String n,int num){
                 goosId=i;
                 name=n;
                 number=num;
             }

             @Override
             public String toString() {
                 return "Test_Stock{" +
                         "goosId=" + goosId +
                         ", name='" + name + '\'' +
                         ", number=" + number +
                         '}';
             }
         }
         Map map = new HashMap();
         Test_Stock t1 = new Test_Stock(1001,"苹果",5);
         Test_Stock t2 = new Test_Stock(1002,"华为",9);
         Test_Stock t3 = new Test_Stock(1003,"小米",3);
         Test_Stock t4 = new Test_Stock(1001,"苹果",2);
         Test_Stock t5 = new Test_Stock(1002,"华为",6);
         Test_Stock t6 = new Test_Stock(1003,"小米",7);
         List<Test_Stock> list = new ArrayList<>();
         list.add(t1);
         list.add(t2);
         list.add(t3);
         list.add(t4);
         list.add(t5);
         list.add(t6);
         for(Test_Stock t:list){
             Test_Stock i = (Test_Stock) map.put(t.goosId,t);
             if(i!=null){
                 map.put(t.goosId,new Test_Stock(t.goosId,t.name,t.number+i.number));
                 System.out.println(i);
             }
         }
        List<Test_Stock> stock = new ArrayList<>();
         Set<Map.Entry<Integer,Test_Stock>> set = map.entrySet();
         for(Map.Entry<Integer,Test_Stock> node:set){
             stock.add(node.getValue());
         }
        System.out.println(stock);
        */
            /*测试合并两个类型相同的list*/
            List<String> list1 = new ArrayList<String>();
            List<String> list2 = new ArrayList<String>();
            //给list1赋值
            list1.add("测");
            list1.add("试");
            list1.add("一");
            list1.add("下");
            //给list2赋值
            list2.add("合");
            list2.add("并");
            list2.add("列");
            list2.add("表");
            //将list1.list2合并
            list1.addAll(list2);
            //循环输出list1 看看结果
            for (String s : list1) {
                System.out.print(s);
            }

    }

}
