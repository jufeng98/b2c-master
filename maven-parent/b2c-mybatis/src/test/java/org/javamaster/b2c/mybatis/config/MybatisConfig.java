package org.javamaster.b2c.mybatis.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yu
 * @date 2018/4/27
 */
@Configuration
@MapperScan(basePackages = "org.javamaster.b2c.mybatis.mapper.mysql")
public class MybatisConfig {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
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
        logger.info("driverClassName:{},url:{},username:{},password:{}", driverClassName, url, username, password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    @SneakyThrows
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        final String mapperLocation = "classpath*:mapper/mysql/**/*.xml";
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resourcePatternResolver.getResources(mapperLocation));
        sessionFactory.setTransactionFactory(new SpringManagedTransactionFactory());
        String configLocation = "classpath*:mybatis-config.xml";
        sessionFactory.setConfigLocation(resourcePatternResolver.getResources(configLocation)[0]);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate mysqlSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
