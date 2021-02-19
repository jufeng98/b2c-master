package com.javamaster.b2c.cloud.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    /**
     * 配置请求路径的安全性控制
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/actuator/**").hasAuthority("ROLE_ROOT")
                .antMatchers("/root/**").access("hasRole('ROOT')")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll().and()
                .formLogin()
                .and()
                .requiresChannel().antMatchers("/root/**").requiresSecure()
                .and()
                .requiresChannel().antMatchers("/home/**").requiresInsecure();
    }

    /**
     * 配置授权信息,这里基于数据库
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
