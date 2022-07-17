package com.chen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig  implements CommandLineRunner {

    @Value("${server.port}")
    private String port ;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("port="+port);
            Runtime.getRuntime().exec("cmd /c start http://localhost:" + port + "/login");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
