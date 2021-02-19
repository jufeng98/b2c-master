package com.javamaster.b2c.cloud.test.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
@Configuration
public class DatabaseConfig {

    //    @Value("${db.mysql.driverClassName}")
    private String driverClassName = "com.mysql.jdbc.Driver";
    //    @Value("${db.mysql.url}")
    private String url = B2cMasterConsts.Local.URL_SAKILA;
    //    @Value("${db.mysql.username}")
    private String username = B2cMasterConsts.Local.USERNAME;
    //    @Value("${db.mysql.password}")
    private String password = B2cMasterConsts.Local.PASSWORD;

    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(10000);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestOnBorrow(true);

        // dataSource.setRemoveAbandoned(true);
        // dataSource.setRemoveAbandonedTimeout(1800);

        dataSource.setValidationQuery("select sysdate from dual");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.javamaster.b2c.**.model");

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        properties.setProperty("dialect", "MySQL");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        bean.setTypeHandlersPackage("com.javamaster.b2c.cloud.test.user.handler");

        final String MAPPER_LOCATION = "classpath*:mapper/**/*.xml";
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager mysqlTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
