package com.me.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author 郭奎
 * @version 1.0
 *
 * 1、作用：放开请求  ，网关只做资源服务器进行权限校验
 **/
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    WebSecurityConfigurerAdapter
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        SecurityWebFilterChain chain = http.authorizeExchange()
                // 白名单放行
                .pathMatchers("/**").permitAll()
                .and().csrf().disable().build();
        // 设置自定义登录参数转换器

        return chain;

    }
}