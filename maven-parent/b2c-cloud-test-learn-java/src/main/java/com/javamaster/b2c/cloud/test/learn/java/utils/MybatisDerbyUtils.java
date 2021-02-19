package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.util.List;

/**
 * @author yu
 * @date 2018/4/19
 */
public class MybatisDerbyUtils {
    public static DruidDataSource dataSource;
    public static JdbcTemplate jdbcTemplate;
    public static TransactionFactory transactionFactory;
    public static SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory;
        }
        dataSource = new DruidDataSource();

        // dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        // dataSource.setUrl("jdbc:derby://localhost:1527/sakila;create=true");

        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUrl("jdbc:derby:sakila");

        dataSource.setUsername("APP");
//        dataSource.setPassword("root");
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(10000);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("values current_date");

        transactionFactory = new JdbcTransactionFactory();
        jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            List<String> sqlLines = Files.readAllLines(ResourceUtils.getFile("classpath:derby-script.sql").toPath());
            for (String sqlLine : sqlLines) {
                sqlLine = sqlLine.replace(";", "");
                jdbcTemplate.execute(sqlLine);
            }

            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);
            sessionFactoryBean.setTransactionFactory(transactionFactory);
            final String MAPPER_LOCATION = "classpath*:mapper/**/*.xml";
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
            String configLocation = "classpath*:mybatis-config.xml";
            sessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResources(configLocation)[0]);
            sessionFactoryBean.getObject().getConfiguration().setCacheEnabled(true);
            sessionFactoryBean.getObject().getConfiguration().setLazyLoadingEnabled(true);
            sqlSessionFactory = sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }
}
