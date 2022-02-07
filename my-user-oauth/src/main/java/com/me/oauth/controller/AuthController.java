package com.me.oauth.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*****
 * @Author: www.itheima
 * @Date: 2019/7/7 16:42
 * @Description: com.changgou.oauth.controller
 ****/
@RestController
@RequestMapping(value = "/userx")
public class AuthController {


    @PostMapping("/login")
    public Result login(String username, String password) {
        if(StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名不允许为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new RuntimeException("密码不允许为空");
        }


        return new Result(true, StatusCode.OK,"登录成功！");
    }

    /***
     * 将令牌存储到cookie
     * @param token
     */
    private void saveCookie(String token){

    }
}
