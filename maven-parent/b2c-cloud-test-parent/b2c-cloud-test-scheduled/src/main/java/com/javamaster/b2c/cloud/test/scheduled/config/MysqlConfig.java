package com.javamaster.b2c.cloud.test.scheduled.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.javamaster.b2c.cloud.test.scheduled.respsitory.SpringSchuduledCronRepository;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author yu
 * @date 2018/4/27
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = SpringSchuduledCronRepository.class, entityManagerFactoryRef = "mysqlEntityManagerFactory")
public class MysqlConfig implements TransactionManagementConfigurer {
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = B2cMasterConsts.Local.URL_SAKILA;
    private String username = B2cMasterConsts.Local.USERNAME;
    private String password = B2cMasterConsts.Local.PASSWORD;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return mysqlTransactionManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return mysqlTransactionManager();
    }

    @Bean
    public DataSource mysqlDataSource() {
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
        dataSource.setValidationQuery("select now()");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean
    public JpaVendorAdapter mysqlJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource,
            @Qualifier("mysqlJpaVendorAdapter") JpaVendorAdapter mysqlJpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(mysqlDataSource);
        bean.setJpaVendorAdapter(mysqlJpaVendorAdapter);
        bean.setPackagesToScan("com.javamaster.b2c.cloud.test.scheduled.entity");
        bean.setPersistenceUnitName("mysqlDataSource");
        return bean;
    }

}
