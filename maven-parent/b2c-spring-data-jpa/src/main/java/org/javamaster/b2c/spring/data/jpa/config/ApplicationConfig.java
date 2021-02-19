package org.javamaster.b2c.spring.data.jpa.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.javamaster.b2c.config.B2cMasterConsts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author yudong
 * @date 2020/7/9
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.javamaster.b2c.spring.data.jpa.repository")
@EnableTransactionManagement
class ApplicationConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(B2cMasterConsts.Local.URL_SAKILA);
        basicDataSource.setUsername(B2cMasterConsts.Local.USERNAME);
        basicDataSource.setPassword(B2cMasterConsts.Local.PASSWORD);
        return basicDataSource;
    }

    /**
     * 使用LocalContainerEntityManagerFactoryBean之后persistence.xml文件就完全没有存在的必要了.
     * 创建容器管理类型的EntityManagerFactory
     * <p>
     * 只有使用LocalEntityManagerFactoryBean才会需要persistence.xml文件,这个文件必须位于类路径
     * META-INF目录下.创建应用管理类型的EntityManagerFactory
     * <p>
     * jpa配置的核心bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        // 指定使用哪个厂商的JPA实现
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("org.javamaster.b2c.spring.data.jpa.entity");
        factory.setDataSource(dataSource());
        return factory;
    }

    /**
     * hibernate配置的核心bean
     */
    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("org.javamaster.b2c.spring.data.jpa.entity");
        return localSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory);
        return hibernateTemplate;
    }
}
