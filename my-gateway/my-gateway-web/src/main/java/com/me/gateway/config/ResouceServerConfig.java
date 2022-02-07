//package com.me.gateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * @author guokui
// * @class MyShop
// * @date 2022/1/24 16:21
// */
//@Configuration
//public class ResouceServerConfig {
//    public static final String RESOURCE_ID = "res1";
//    /*** 统一认证服务(UAA) 资源拦截 */
//    @Configuration
//    @EnableResourceServer
//    public class UAAServerConfig extends ResourceServerConfigurerAdapter {
//        @Autowired
//        private TokenStore tokenStore;
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources){
//            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID) .stateless(true);
//        }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests() .antMatchers("/uaa/**").permitAll(); }
//    }/*** 订单服务 */
//
//    /**网关不进行拦截。 OrderServerConfig指定了若请求匹配/order/**，也就是访问统一用户服务，接入客户端需要有scope中包含 read，并且authorities(权限)中需要包含ROLE_USER。 由于res1这个接入客户端，read包括ROLE_ADMIN,ROLE_USER,ROLE_API三个权限。 7.3.4 安全配置
//     *
//     */
//    @Configuration
//    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http .authorizeRequests()
//                    .antMatchers("/**")
//                    .permitAll()
//                    .and().csrf().disable();
//        }
//    }
//   // 7.3.转发明文token给微服务 通过Zuul过滤器的方式实现，目的是让下游微服务能够很方便的获取到当前的登录用户信息（明文token）
//    @Configuration
//    @EnableResourceServer
//    public class OrderServerConfig extends ResourceServerConfigurerAdapter {
//        @Autowired
//        private TokenStore tokenStore;
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) {
//            resources.tokenStore(tokenStore).resourceId(RESOURCE_ID) .stateless(true);
//        }
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http .authorizeRequests() .antMatchers("/order/**")
//                    .access("#oauth2.hasScope('ROLE_API')");
//        }
//    }
//}