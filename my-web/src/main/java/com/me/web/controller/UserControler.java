package com.me.web.controller;

import com.me.web.controller.Userinterface.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 *@ClassName LoginControler
 *@Deacription TODO
 *@Author
 *@Date 2021/3/31 17:34
 *@Version 1.0
 **/
@RestController
public class UserControler {
    @Bean
    @LoadBalanced
    public RestTemplate getResttemplate(){
        return new RestTemplate();
    }
    @Autowired
    private RestTemplate resttemplate;
    private UserClient feignClient;

    @RequestMapping("/brand/list")
    public String hello(){
        //指出服务地址   http://{服务提供者应用名名称}/{具体的controller}
        String url="http://goods/brand/list";
        //返回值类型和我们的业务返回值一致
        System.out.println("beidiaoyong1");
        return resttemplate.getForObject(url, String.class);
    }
    @RequestMapping("/brand01")
    public String hello01(){

        //返回值类型和我们的业务返回值一致
        return feignClient.findAll();
    }
}
