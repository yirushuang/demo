package com.wyp.cmsService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@ComponentScan({"com.wyp"})
@MapperScan("com.wyp.cmsService.mapper")
@SpringBootApplication
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
