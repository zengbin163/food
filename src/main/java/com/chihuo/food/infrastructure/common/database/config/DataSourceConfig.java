package com.chihuo.food.infrastructure.common.database.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSource;
import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSourceHolder;

@Configuration
public class DataSourceConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Autowired
	private DataSourceProperties properties;
	
    /**
     * 配置数据源
     * 
     * @return
     */
    @Bean(name = "master")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource master() {
		LOGGER.info("init master data source ---> properties：{}", properties);
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public DataSource slave() {
		LOGGER.info("init slave data source ---> properties：{}", properties);
        return DataSourceBuilder.create().build();
    }
	
    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("master") DataSource master, @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceHolder.DB_MASTER, master);
        targetDataSource.put(DynamicDataSourceHolder.DB_SLAVE, slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        return dataSource;
    }
}
