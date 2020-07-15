package com.chihuo.food.infrastructure.common.database.config;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSource;
import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSourceInterceptor;

/**
 * @author zengbin
 * @Date 2019/6/8 16:12
 */
@Configuration // 该注解类似于spring配置文件
@EnableTransactionManagement
@MapperScan(basePackages = "com.chihuo.food.domain.*.repository.mapper*")
public class MyBatisPlusConfig implements TransactionManagementConfigurer {

	@Autowired
	private DataSourceConfig config;
	
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor pageInterceptor = new PaginationInterceptor();
        pageInterceptor.setDialect(new MySqlDialect());
        pageInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return pageInterceptor;
    }

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor() {
        return new DynamicDataSourceInterceptor();
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        // 配置mybatis,对应mybatis-config.xml
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        // 懒加载
        LazyConnectionDataSourceProxy p = new LazyConnectionDataSourceProxy();
        p.setTargetDataSource(config.dataSource(config.master(), config.slave()));
        sqlSessionFactory.setDataSource(p);
        sqlSessionFactory.setTypeAliasesPackage("com.chihuo.food.domain.*.repository.po");
        // 需要mapper文件并且不在同一个包下时加入扫描，
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactory.setConfiguration(configuration);
        // 加入上面的两个拦截器
        Interceptor interceptor[] = {paginationInterceptor(), dynamicDataSourceInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        sqlSessionFactory.setGlobalConfig(globalConfig());
        return sqlSessionFactory.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
			return transactionManager(config.dataSource(config.master(), config.slave()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.AUTO);
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

}
