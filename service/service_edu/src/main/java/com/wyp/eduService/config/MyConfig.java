package com.wyp.eduService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wyp.eduService.mapper")
public class MyConfig {

//  逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

//  配置分页插件工具
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }




}
