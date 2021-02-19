package org.javamaster.b2c.batch.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        dataSource.setDriverClassName(driverClassName);
        String url = B2cMasterConsts.Local.URL_SAKILA;
        dataSource.setJdbcUrl(url);
        String username = B2cMasterConsts.Local.USERNAME;
        dataSource.setUsername(username);
        String password = B2cMasterConsts.Local.PASSWORD;
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("org.javamaster.b2c.**.model");
        bean.setTypeHandlersPackage("org.javamaster.b2c.handler");
        String mapperLocation = "classpath*:mapper/**/*.xml";
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager mysqlTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
