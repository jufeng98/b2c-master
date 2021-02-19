package org.javamaster.b2c.mybatis.dbconfigs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author yu
 * @date 2018/4/27
 */
@Configuration
@MapperScan(basePackages = "org.javamaster.b2c.mybatis.mapper.h2", sqlSessionFactoryRef = "h2SqlSessionFactory", sqlSessionTemplateRef = "h2SqlSessionTemplate")
public class H2Config {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @Primary
    public DataSource h2DataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("h2-schema.sql")
                .addScript("h2-data.sql")
                .build();
        logger.info("h2DataSource start to create:driverClassName:{},url:{},username:{},password:{}"
                , "", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false", "sa", "");
        return dataSource;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager h2TransactionManager(DataSource h2DataSource) {
        return new DataSourceTransactionManager(h2DataSource);
    }

    @Bean
    @Primary
    public JdbcTemplate h2JdbcTemplate(DataSource h2DataSource) {
        return new JdbcTemplate(h2DataSource);
    }

    /**
     * mybatis配置的核心bean
     */
    @Bean
    @Primary
    public SqlSessionFactory h2SqlSessionFactory(DataSource h2DataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(h2DataSource);
        String mapperLocation = "classpath*:mapper/h2/**/*.xml";
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        String configLocation = "classpath*:h2-mybatis-config.xml";
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResources(configLocation)[0]);
        Objects.requireNonNull(sessionFactory.getObject()).getConfiguration().setCacheEnabled(true);
        sessionFactory.getObject().getConfiguration().setLazyLoadingEnabled(true);
        sessionFactory.getObject().getConfiguration().setObjectFactory(new SpecObjectFactory());
        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate h2SqlSessionTemplate(SqlSessionFactory h2SqlSessionFactory) {
        return new SqlSessionTemplate(h2SqlSessionFactory);
    }

}
