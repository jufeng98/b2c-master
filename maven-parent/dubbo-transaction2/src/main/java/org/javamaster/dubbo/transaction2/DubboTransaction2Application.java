package org.javamaster.dubbo.transaction2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yudong
 * @date 2020/6/29
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
@ComponentScan("org.javamaster")
// @ImportResource({"classpath:tcc-transaction.xml"})
public class DubboTransaction2Application {

    public static void main(String[] args) {
        SpringApplication.run(DubboTransaction2Application.class, args);
    }

    // @Bean
    // public TransactionRepository transactionRepository() {
    //     SpringJdbcTransactionRepository springJdbcTransactionRepository = new SpringJdbcTransactionRepository();
    //     springJdbcTransactionRepository.setDataSource(dataSource());
    //     return springJdbcTransactionRepository;
    // }
    //
    // public DataSource dataSource() {
    //     DruidDataSource dataSource = new DruidDataSource();
    //     dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    //     dataSource.setUrl(B2cMasterConsts.Local.URL_SAKILA);
    //     dataSource.setUsername(B2cMasterConsts.Local.USERNAME);
    //     dataSource.setPassword(B2cMasterConsts.Local.PASSWORD);
    //     dataSource.setInitialSize(10);
    //     dataSource.setMaxActive(20);
    //     dataSource.setMaxWait(10000);
    //     dataSource.setDefaultAutoCommit(true);
    //     dataSource.setRemoveAbandoned(true);
    //     dataSource.setTestOnBorrow(true);
    //     dataSource.setValidationQuery("select now()");
    //     return dataSource;
    // }

}
