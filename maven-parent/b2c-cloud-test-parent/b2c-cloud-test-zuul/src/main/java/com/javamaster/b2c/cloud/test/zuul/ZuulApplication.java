package com.javamaster.b2c.cloud.test.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;

@EnableZuulProxy
@EnableDiscoveryClient
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = ProjectConst.REDIS_SESSION_EXPIRED_TIME)
@ComponentScan(basePackages = "com.javamaster.b2c")
@SpringBootApplication
public class ZuulApplication {

    @Autowired
    Environment environment;
    @Autowired
    DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        System.out.println(environment.getProperty("test.uuid"));
        System.out.println(environment.getProperty("test"));
        System.out.println(environment.getProperty("test.ten"));
        System.out.println(environment.getProperty("test.range"));
        System.out.println(environment.getProperty("test.long"));
        System.out.println(environment.getProperty("db.username"));
        System.out.println(discoveryClient.getInstances("b2c-cloud-integration-interfaces"));
        return new CookieLocaleResolver();
    }

    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>.*)-(?<version>v.*$)", "${version}/${name}");
    }
}
