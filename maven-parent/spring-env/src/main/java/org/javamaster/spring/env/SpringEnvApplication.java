package org.javamaster.spring.env;

import org.javamaster.spring.env.props.PersonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @author yudong
 * @date 2020/7/31
 */
@SpringBootApplication
public class SpringEnvApplication {

    private static Logger logger = LoggerFactory.getLogger(SpringEnvApplication.class);

    @Autowired
    private Environment env;
    @Autowired
    private PersonProperties personProperties;

    public static void main(String[] args) {
        System.setProperty("active.profile.name", "dev");
        SpringApplication.run(SpringEnvApplication.class, args);
    }

    @PostConstruct
    public void showProps() {
        // 本项目yml获取properties的random配置
        logger.info("env config:{}", env.getProperty("env.my.secret"));
        logger.info("env config:{}", env.getProperty("env.my.number"));
        logger.info("env config:{}", env.getProperty("env.my.bignumber"));
        logger.info("env config:{}", env.getProperty("env.my.uuid"));
        logger.info("env config:{}", env.getProperty("env.my.number.less.than.ten"));
        logger.info("env config:{}", env.getProperty("env.my.number.in.range"));
        logger.info("env config:{}", env.getProperty("env.my.composite"));

        // 本项目yml获取不同环境配置
        logger.info("env yml config:{}", env.getProperty("env.common"));
        logger.info("env yml config:{}", env.getProperty("env.environment"));
        logger.info("env yml config:{}", env.getProperty("feign.secret.url"));

        // 本项目yml获取数组类型配置
        logger.info("env yml config:{}", personProperties);
    }

}
