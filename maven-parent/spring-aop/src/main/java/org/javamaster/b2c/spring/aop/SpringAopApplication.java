package org.javamaster.b2c.spring.aop;

import org.javamaster.b2c.spring.aop.service.impl.AopServiceImpl;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * @author yudong
 * @date 2020/6/4
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setPattern(".*getSimple.*");
        advisor.setAdvice((AfterReturningAdvice) (returnValue, method, args, target) -> {
            System.out.println(returnValue);
            System.out.println(method.getName());
            System.out.println(Arrays.toString(args));
        });
        JdkRegexpMethodPointcut pointcut = (JdkRegexpMethodPointcut) advisor.getPointcut();
        pointcut.setClassFilter(clazz -> AopServiceImpl.class == clazz);
        return advisor;
    }

}
