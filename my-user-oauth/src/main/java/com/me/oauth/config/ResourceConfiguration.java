package com.me.oauth.config;

import com.me.oauth.service.impl.MyClientDetialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

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
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private TokenStore tokenStore;

    @Autowired
    private MyClientDetialService clientDetailsService;
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;
    /**
     * 用来配置令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
//    @Override
//    public void configure(ResourceServerSecurityConfigurer endpoints) throws Exception {
//            endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager);
//    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();  //受保护资源url: /userInfo 需要认证
    }
}