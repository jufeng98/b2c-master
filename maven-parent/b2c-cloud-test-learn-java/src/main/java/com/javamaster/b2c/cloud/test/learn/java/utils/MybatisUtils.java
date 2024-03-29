package com.javamaster.b2c.cloud.test.learn.java.utils;

import com.alibaba.druid.pool.DruidDataSource;
import static com.javamaster.b2c.cloud.test.learn.java.utils.PropertiesUtils.getProp;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created on 2019/3/20.<br/>
 *
 * @author yudong
 */
public class MybatisUtils {
    public static PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private static DruidDataSource dataSourceMicrowebsite;
    private static DruidDataSource dataSourceJdbcMicrowebsite;
    private static DruidDataSource dataSourceJdbcSakila;
    private static DruidDataSource dataSourceJdbcMoonMall;
    private static DruidDataSource dataSourceJdbcWashingService;
    private static DruidDataSource dataSourceJdbcWashMall;
    private static DruidDataSource dataSourceJdbcOnlineExam;
    private static DruidDataSource dataSourceJdbcB2b2cMall;

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    private static JdbcTemplate jdbcTemplateSakila;
    private static JdbcTemplate jdbcTemplateWashMall;
    private static JdbcTemplate jdbcTemplateMoonMall;
    private static JdbcTemplate jdbcTemplateB2b2cMall;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory != null) {
            return sqlSessionFactory;
        }
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(getDatasourceMicrowebsite());
            final String mapperLocation = "classpath*:mapper/**/*.xml";
            sessionFactory.setMapperLocations(resourcePatternResolver.getResources(mapperLocation));
            sessionFactory.setTransactionFactory(new JdbcTransactionFactory());
            String configLocation = "classpath*:mybatis-config.xml";
            sessionFactory.setConfigLocation(resourcePatternResolver.getResources(configLocation)[0]);
            sqlSessionFactory = sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        if (sqlSession != null) {
            return sqlSession;
        }
        sqlSession = getSqlSessionFactory().openSession();
        return sqlSession;
    }

    public static JdbcTemplate getJdbcTemplateSakila() {
        if (jdbcTemplateSakila != null) {
            return jdbcTemplateSakila;
        }
        jdbcTemplateSakila = new JdbcTemplate(getDatasourceJdbcSakila());
        return jdbcTemplateSakila;
    }

    public static JdbcTemplate getJdbcTemplateWashMall() {
        if (jdbcTemplateWashMall != null) {
            return jdbcTemplateWashMall;
        }
        jdbcTemplateWashMall = new JdbcTemplate(getDatasourceJdbcWashMall());
        return jdbcTemplateWashMall;
    }

    public static JdbcTemplate getJdbcTemplateMoonMall() {
        if (jdbcTemplateMoonMall != null) {
            return jdbcTemplateMoonMall;
        }
        jdbcTemplateMoonMall = new JdbcTemplate(getDatasourceJdbcMoonMall());
        return jdbcTemplateMoonMall;
    }

    public static JdbcTemplate getJdbcTemplateB2b2cMall() {
        if (jdbcTemplateB2b2cMall != null) {
            return jdbcTemplateB2b2cMall;
        }
        jdbcTemplateB2b2cMall = new JdbcTemplate(getDatasourceJdbcB2b2cMall());
        return jdbcTemplateB2b2cMall;
    }

    public static DruidDataSource getDatasourceMicrowebsite() {
        if (dataSourceMicrowebsite != null) {
            return dataSourceMicrowebsite;
        }
        dataSourceMicrowebsite = druidDataSource(getProp("Micro.URL"), getProp("Micro.USERNAME"), getProp("Micro_PASSWORD"));
        return dataSourceMicrowebsite;
    }

    public static DruidDataSource getDatasourceJdbcMicrowebsite() {
        if (dataSourceJdbcMicrowebsite != null) {
            return dataSourceJdbcMicrowebsite;
        }
        dataSourceJdbcMicrowebsite = druidDataSource(getProp("Micro.URL"), getProp("Micro.USERNAME"), getProp("Micro_PASSWORD"));
        return dataSourceJdbcMicrowebsite;
    }

    public static DruidDataSource getDatasourceJdbcSakila() {
        if (dataSourceJdbcSakila != null) {
            return dataSourceJdbcSakila;
        }
        dataSourceJdbcSakila = druidDataSource(getProp("Local.URL_SAKILA"), getProp("Local.USERNAME"), getProp("Local.PASSWORD"));
        return dataSourceJdbcSakila;
    }

    public static DruidDataSource getDatasourceWashingService() {
        if (dataSourceJdbcWashingService != null) {
            return dataSourceJdbcWashingService;
        }
        dataSourceJdbcWashingService = druidDataSource(getProp("WashingService.URL"), getProp("WashingService.USERNAME"),
                getProp("WashingService.PASSWORD"));
        // dataSourceJdbcWashingService = druidDataSource(URL_SAKILA, getProp("Local.USERNAME, getProp("Local.PASSWORD);
        return dataSourceJdbcWashingService;
    }

    public static DruidDataSource getDatasourceJdbcWashingService() {
        if (dataSourceJdbcWashingService != null) {
            return dataSourceJdbcWashingService;
        }
        dataSourceJdbcWashingService = druidDataSource(getProp("WashingService.URL"), getProp("WashingService.USERNAME"),
                getProp("WashingService.PASSWORD"));
        return dataSourceJdbcWashingService;
    }

    public static DruidDataSource getDatasourceJdbcWashMall() {
        if (dataSourceJdbcWashMall != null) {
            return dataSourceJdbcWashMall;
        }
        dataSourceJdbcWashMall = druidDataSource(getProp("Honor.URL_WASH"), getProp("WashingService.USERNAME"), getProp("WashingService.PASSWORD"));
        return dataSourceJdbcWashMall;
    }

    public static DruidDataSource getDatasourceJdbcMoonMall() {
        if (dataSourceJdbcMoonMall != null) {
            return dataSourceJdbcMoonMall;
        }
        dataSourceJdbcMoonMall = druidDataSource(getProp("Honor.URL_MOON"), getProp("WashingService.USERNAME"), getProp("WashingService.PASSWORD"));
        // dataSourceJdbcMoonMall = druidDataSource(getProp("Honor.URL_MOON_1, getProp("WashingService.USERNAME, getProp("WashingService.PASSWORD);
        return dataSourceJdbcMoonMall;
    }

    public static DruidDataSource getDataSourceJdbcOnlineExam() {
        if (dataSourceJdbcOnlineExam != null) {
            return dataSourceJdbcOnlineExam;
        }
        dataSourceJdbcOnlineExam = druidDataSource(getProp("Local.URL_ONLINE_EXAM"), getProp("Local.USERNAME"), getProp("Local.PASSWORD"));
        return dataSourceJdbcOnlineExam;
    }

    public static DruidDataSource getDatasourceJdbcB2b2cMall() {
        if (dataSourceJdbcB2b2cMall != null) {
            return dataSourceJdbcB2b2cMall;
        }
        dataSourceJdbcB2b2cMall = druidDataSource(getProp("Honor.URL_B2B2C_MALL"), getProp("WashingService.USERNAME"), getProp("WashingService.PASSWORD"));
        return dataSourceJdbcB2b2cMall;
    }

    public static DruidDataSource druidDataSource(String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(5000);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select now()");
        return dataSource;
    }
}
