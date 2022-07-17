package com.chen;

import com.chen.monitor_data.TimeSimple;
import com.chen.service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@SpringBootTest
class MonitorApplicationTests {

    @Test
    void contextLoads() {
        char a='a';
        char b='b';
        int ce = a+b;
        char cs = (char) (a+b);
        class max1{
            String name;
            Double data;
            public max1(String name,Double data){
                this.name = name;
                this.data = data;
            }
        }
        TreeSet<max1> dd = new TreeSet<max1>(new Comparator<max1>() {
            @Override
            public int compare(max1 o1, max1 o2) {
                System.out.println("o1="+o1.data);
                System.out.println("o2="+o2.data);
                return o1.data>o2.data? -1 : 1 ;
            }
        });
        dd.add(new max1("o1",3.0));
        dd.add(new max1("o2",10.0));
        dd.add(new max1("o3",5.0));
        max1 first = dd.first();
        System.out.println(first.name+first.data);
        for(max1 cc:dd){
            System.out.println(cc.name+cc.data);
        }
    }

    @Autowired
    private MailService mailService;

    @Test
    void contextLoads02(){
        for(int i=0;i<10;i++){
            System.out.println(i);
        }
    }

}
