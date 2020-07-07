package com.chihuo.food.infrastructure.common.database.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceMaker {
	
    /**
     * 配置数据源
     * 
     * @return
     */
    @Bean(name = "master")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public static DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public static DataSource slave() {
        return DataSourceBuilder.create().build();
    }
}
