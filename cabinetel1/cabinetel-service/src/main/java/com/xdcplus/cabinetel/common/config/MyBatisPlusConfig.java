package com.xdcplus.cabinetel.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author : Fish Fei
 */
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.xdcplus.cabinetel.basic.mapper.*")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     */

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}