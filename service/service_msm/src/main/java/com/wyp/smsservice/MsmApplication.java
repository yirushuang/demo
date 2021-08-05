package com.wyp.smsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.wyp")
public class MsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
