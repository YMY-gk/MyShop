package com.me.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author guokui
 * @class srs
 * @date 2021/9/27 16:09
 */
@Slf4j
@Configuration
@EnableResourceServer
@Order(3)
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {

    @Resource
    private TokenStore tokenStore;

    /**
     * 用来配置令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore).stateless(true);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**") //表示配置请求路径
                .permitAll()// 指定 URL 无需保护。
                .anyRequest().authenticated()
                ;
    }
}