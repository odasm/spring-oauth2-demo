package com.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.Filter;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/31 11:54
 * Description:
 */

@SpringBootApplication
@ComponentScan("com.example")
public class SocialApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }

    @Autowired
    @Qualifier("userLogInFilter")
    Filter userLogInFilter;

    @Autowired
    @Qualifier("loginUrlFilterEntryPoint")
    AuthenticationEntryPoint entryPoint;


    @Autowired
    @Qualifier("csrfTokenRepository")
    CsrfTokenRepository csrfTokenRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/login**")
                .permitAll()
                .anyRequest()
                .not()
                .anonymous()
                .and().exceptionHandling()
                .authenticationEntryPoint(entryPoint).and().logout()
                .logoutUrl("j_spring_security_logout")
                .and().csrf()
                .csrfTokenRepository(csrfTokenRepository).and()
                .addFilterBefore(userLogInFilter, BasicAuthenticationFilter.class);
        // @formatter:on
    }
}
