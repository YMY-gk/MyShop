package com.me.oauth.service.impl;

import com.me.user.pojo.TbUser;
import entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author guokui
 * @class security
 * @date 2021/9/2 17:06
 */
@Service("UserDetailsService")
@Slf4j
public class MyUserDetialService implements UserDetailsService {
    @Autowired
    private UserClient loginClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Result< TbUser > user =loginClient.findByUserId(username);

        // 判断用户名是否存在
        if (user==null||user.getData()==null){
            log.error("-----------------------------loadUserByUsername-----------------------------------");
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 从数据库中获取的密码 atguigu 的密文
        String pwd ="lisi";//user.getData().getPassword();
        user.getData().setPassword(pwd);
        // 第三个参数表示权限
        return new User(username,new BCryptPasswordEncoder().encode(pwd), AuthorityUtils.commaSeparatedStringToAuthorityList("user,ROLE_role1"));
    }
}
