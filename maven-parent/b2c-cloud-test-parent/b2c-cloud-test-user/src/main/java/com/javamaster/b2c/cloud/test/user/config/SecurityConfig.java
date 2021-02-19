package com.javamaster.b2c.cloud.test.user.config;

import com.javamaster.b2c.cloud.test.user.service.impl.LoginHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

/**
 * Created on 2018/12/8.<br/>
 *
 * @author yudong
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsService userDetailsService;
    // @Autowired
    // private FindByIndexNameSessionRepository sessionRepository;

    // private static String secret = "295rx6adw8eb4";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**", "/home/**", "/img/**").permitAll()
                .antMatchers("/actuator/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/user/login")
                .successHandler(LoginHandler::onAuthenticationSuccess)
                .failureHandler(LoginHandler::onAuthenticationFailure)
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutSuccessHandler(LoginHandler::onLogoutSuccess)
                .and()
                .csrf().disable();
        //.sessionManagement()
        //.maximumSessions(1)
        //.sessionRegistry(sessionRegistry());
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ProviderManager providerManager() {
        return new ProviderManager(Arrays.asList(new TestingAuthenticationProvider()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


    // @Bean
    // public SpringSessionBackedSessionRegistry sessionRegistry() {
    //     return new SpringSessionBackedSessionRegistry(sessionRepository);
    // }

}