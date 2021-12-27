package com.me.user.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.me.user.pojo.TbUser;
import com.me.user.service.impl.TbUserServiceImpl;
import entity.BCrypt;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtsUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@RestController
@RequestMapping("/user")
public class TbUserController {
    @Autowired
    TbUserServiceImpl userServiceImpl;
    /***
     * 用户登录
     * 1.用户通过访问微服务网关调用微服务，同时携带头文件信息
     * 2.在微服务网关这里进行拦截，拦截后获取用户要访问的路径
     * 3.识别用户访问的路径是否需要登录，如果需要，识别用户的身份是否能访问该路径[这里可以基于数据库设计一套权限]
     * 4.如果需要权限访问，用户已经登录，则放行
     * 5.如果需要权限访问，且用户未登录，则提示用户需要登录
     * 6.用户通过网关访问用户微服务，进行登录验证
     * 7.验证通过后，用户微服务会颁发一个令牌给网关，网关会将用户信息封装到头文件中，并响应用户
     * 8.用户下次访问，携带头文件中的令牌信息即可识别是否登录
     */
    @RequestMapping(value = "/login")
    public Result login(String username, String password){
        //查询用户信息
        TbUser user = userServiceImpl.getById(username);

        if(user!=null && BCrypt.checkpw(password,user.getPassword())){
            //设置令牌信息
            Map<String,Object> info = new HashMap<String,Object>();
            info.put("role","USER");
            info.put("success","SUCCESS");
            info.put("username",username);
            //生成令牌
            String jwt = JwtsUtils.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info),null);
            return new Result(true,StatusCode.OK,"登录成功！",jwt);
        }
        return  new Result(false,StatusCode.LOGINERROR,"账号或者密码错误！");
    }
}
