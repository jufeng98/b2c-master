package org.javamaster.b2c.mybatis.dbconfigs;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.javamaster.b2c.mybatis.enums.EnumBase;
import org.javamaster.b2c.mybatis.model.PhoneNumber;
import org.javamaster.b2c.mybatis.typehandler.EnumBaseTypeHandler;
import org.javamaster.b2c.mybatis.typehandler.PhoneNumberTypeHandler;
import org.javamaster.b2c.mybatis.utils.ClassUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yu
 * @date 2018/4/27
 */
@Configuration
@MapperScan(basePackages = "org.javamaster.b2c.mybatis.mapper.mysql", sqlSessionFactoryRef = "mysqlSqlSessionFactory", sqlSessionTemplateRef = "mysqlSqlSessionTemplate")
public class MySqlConfig {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public DataSource mysqlDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        dataSource.setDriverClassName(driverClassName);
        String url = B2cMasterConsts.Local.URL_SAKILA;
        dataSource.setJdbcUrl(url);
        String username = B2cMasterConsts.Local.USERNAME;
        dataSource.setUsername(username);
        String password = B2cMasterConsts.Local.PASSWORD;
        dataSource.setPassword(password);
        logger.info("mysqlDataSource start to create:driverClassName:{},url:{},username:{},password:{}"
                , driverClassName, url, username, password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        return new JdbcTemplate(mysqlDataSource);
    }

    /**
     * mybatis配置的核心bean
     */
    @Bean
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource)
            throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlDataSource);
        String mapperLocation = "classpath*:mapper/mysql/**/*.xml";
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));

        // 只指定包名,则mybatis会自动为 JavaBean 注册一个小写字母开头的非完全限定的类名形式的别名
        sqlSessionFactoryBean.setTypeAliasesPackage("org.javamaster.b2c.mybatis.model");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(PhoneNumber.class, JdbcType.VARCHAR, new PhoneNumberTypeHandler());
        typeHandlerRegistry.register(PhoneNumber.class, null, new PhoneNumberTypeHandler());
        // 找到EnumBase接口所在的包下所有实现该接口的枚举类
        Set<Class<?>> set = ClassUtils.getAllClassesFromPackage(EnumBase.class.getPackage().getName())
                .stream()
                .filter(clz -> clz.isEnum() && EnumBase.class.isAssignableFrom(clz))
                .collect(Collectors.toSet());
        // 动态注册所有实现了EnumBase接口的枚举类的类型转换器
        set.forEach(enumClass -> {
            EnumBaseTypeHandler handler = new EnumBaseTypeHandler(enumClass);
            typeHandlerRegistry.register(enumClass, JdbcType.TINYINT, handler);
            typeHandlerRegistry.register(enumClass, null, handler);
        });

        configuration.setLogImpl(Slf4jImpl.class);
        configuration.setCacheEnabled(true);
        configuration.addMappers("org.javamaster.b2c.mybatis.mapper.mysql");
        LogFactory.useSlf4jLogging();
        configuration.setLazyLoadingEnabled(true);
        configuration.setObjectFactory(new SpecObjectFactory());

        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate mysqlSqlSessionTemplate(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory mysqlSqlSessionFactory) {
        return new SqlSessionTemplate(mysqlSqlSessionFactory);
    }


}
