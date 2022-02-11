package com.me.gateway.config;

import cn.hutool.core.convert.Convert;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author guokui
 * @class srs
 * @date 2021/9/27 16:09
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    private ResourceServerManager permissionAuthorizationManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/oauth/**").permitAll()
                .anyExchange().access(permissionAuthorizationManager);
        http.csrf().disable();
        return http.build();
    }

}
