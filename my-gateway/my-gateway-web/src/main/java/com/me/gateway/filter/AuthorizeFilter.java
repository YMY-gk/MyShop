package com.me.gateway.filter;

import com.me.gateway.utils.JwtsUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author guokui
 * @class MyShop
 * @date 2021/12/27 17:15
 */
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter, Ordered {
    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    /***
     * 全局过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("1-------开始执行拦截逻辑------------------------------------");



        //放行
        return chain.filter(exchange);
    }

    /***
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        log.info("1-------开始执行------------------------------------");
        return 0;
    }
}
