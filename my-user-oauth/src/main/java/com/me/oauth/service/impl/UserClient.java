package com.me.oauth.service.impl;


import com.me.user.pojo.OauthClientDetails;
import com.me.user.pojo.TbUser;
import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 这个接口相当于把原来的服务提供者项目当成一个Service类
 * @author  yumuyi
 * @date  2021/4/5 21:24
 * @version 1.0
 */
@FeignClient("user")
public interface UserClient {
    /**
     * Feign中没有原生的@GetMapping/@PostMapping/@DeleteMapping/@PutMapping，要指定需要用method进行
     *
     *
     * 接口上方用requestmapping指定是服务提供者的哪个controller提供服务
     */

    @RequestMapping(value= "/user/login",method= RequestMethod.GET)
    public Result login(@RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam("url") String url);
    @GetMapping("/user/{id}")
    public Result< TbUser > findByUserId(@PathVariable String id) ;
    @GetMapping("/oauthClientDetails/{id}")
    public Result< OauthClientDetails > findByOauthClientId(@PathVariable String id);
}