package com.chen;

import com.chen.monitor_data.TimeSimple;
import com.chen.service.Operate_Excel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component

@SpringBootApplication
@MapperScan(basePackages = "com.chen.dao")
public class MonitorApplication{


    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MonitorApplication.class, args);
        System.out.println(TimeSimple.timeSimple());
        System.out.println("执行完毕");
    }

   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }*/
}
