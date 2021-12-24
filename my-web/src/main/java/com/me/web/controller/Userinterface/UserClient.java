package com.me.web.controller.Userinterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 这个接口相当于把原来的服务提供者项目当成一个Service类
 * @author  yumuyi
 * @date  2021/4/5 21:24
 * @version 1.0
 */
@FeignClient("goods")
@Component
public interface UserClient {
    /**
     * Feign中没有原生的@GetMapping/@PostMapping/@DeleteMapping/@PutMapping，要指定需要用method进行
     *
     *
     * 接口上方用requestmapping指定是服务提供者的哪个controller提供服务
     */
    @RequestMapping(value= "/brand/list",method= RequestMethod.GET)
    public String findAll();

}