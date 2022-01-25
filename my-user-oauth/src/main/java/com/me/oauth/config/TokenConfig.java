package com.me.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 令牌配置
 * AuthorizationServerTokenServices 接口定义了一些操作使得你可以对令牌进行一些必要的管理，令牌可以被用来 加载身份信息，里面包含了这个令牌的相关权限。
 * 自己可以创建 AuthorizationServerTokenServices 这个接口的实现，则需要继承 DefaultTokenServices 这个类， 里面包含了一些有用实现，你可以使用它
 * 来修改令牌的格式和令牌的存储。默认的，当它尝试创建一个令牌的时 候，是使用随机值来进行填充的，除了持久化令牌是委托一个 TokenStore 接口来实现以外，这个
 * 类几乎帮你做了 所有的事情。并且 TokenStore 这个接口有一个默认的实现，它就是 InMemoryTokenStore ，如其命名，所有的 令牌是被保存在了内存中。除了使
 * 用这个类以外，你还可以使用一些其他的预定义实现，下面有几个版本，它们都 实现了TokenStore接口： InMemoryTokenStore：这个版本的实现是被默认采用的，它
 * 可以完美的工作在单服务器上（即访问并发量 压力不大的情况下，并且它在失败的时候不会进行备份），大多数的项目都可以使用这个版本的实现来进行 尝试，你可以在开发
 * 的时候使用它来进行管理，因为不会被保存到磁盘中，所以更易于调试。 JdbcTokenStore：这是一个基于JDBC的实现版本，令牌会被保存进关系型数据库。使用这个版本
 * 的实现时， 你可以在不同的服务器之间共享令牌信息，使用这个版本的时候请注意把"spring-jdbc"这个依赖加入到你的 classpath当中。
 *
 * JwtTokenStore：这个版本的全称是 JSON Web Token（JWT），它可以把令牌相关的数据进行编码（因此对 于后端服务来说，它不需要进行存储，这将是一个重大优势），
 * 但是它有一个缺点，那就是撤销一个已经授 权令牌将会非常困难，所以它通常用来处理一个生命周期较短的令牌以及撤销刷新令牌（refresh_token）。 另外一个缺点就是这
 * 个令牌占用的空间会比较大，如果你加入了比较多用户凭证信息。JwtTokenStore 不会保 @Override public void configure(ClientDetailsServiceConfigurer
 * clients) throws Exception { // clients.withClientDetails(clientDetailsService); clients.inMemory()// 使用in‐memory存储 .withClient("c1")
 * // client_id .secret(new BCryptPasswordEncoder().encode("secret")) .resourceIds("res1") .authorizedGrantTypes("authorization_code",
 * "password","client_credentials","implicit","refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,
 * client_credentials .scopes("all")// 允许的授权范围 .autoApprove(false) //加上验证回调地址 .redirectUris("http://www.baidu.com"); }
 * 北京市昌平区建材城西路金燕龙办公楼一层 电话：400-618-9090
 * 存任何数据，但是它在转换令牌值以及授权信息方面与 DefaultTokenServices 所扮演的角色是一样的。
 * @author guokui
 * @class MyShop
 * @date 2022/1/24 17:37
 */
@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
