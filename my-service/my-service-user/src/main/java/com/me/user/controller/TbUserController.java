package com.me.user.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.user.pojo.TbAddress;
import com.me.user.pojo.TbAreas;
import com.me.user.pojo.TbCities;
import com.me.user.pojo.TbUser;
import com.me.user.service.impl.TbUserServiceImpl;
import entity.BCrypt;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import utils.JwtsUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
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
    public Result login(String username, String password, HttpServletResponse response){
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
            Cookie  cookie = new Cookie("Authorization",jwt);
            response.addCookie(cookie);
            return new Result(true,StatusCode.OK,"登录成功！",jwt);
        }
        return  new Result(false,StatusCode.LOGINERROR,"账号或者密码错误！");
    }

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<Page> findPage(@RequestBody(required = false) TbUser user, @PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页条件查询User
        Page< TbUser > pagez = new Page<>(page,size);
        QueryWrapper< TbUser > queryWrapper =this.createExample(user);
        Page<TbUser> pageInfo = userServiceImpl.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<Page> findPage(@PathVariable int page, @PathVariable int size) {
        //调用UserService实现分页查询User
        Page< TbUser > pagez = new Page<>(page,size);
        Page<TbUser> pageInfo = userServiceImpl.page(pagez);

        return new Result<Page>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @PostMapping(value = "/search")
    public Result< List<TbUser> > findList(@RequestBody(required = false) TbUser user) {
        //调用UserService实现条件查询User
        QueryWrapper< TbUser > queryWrapper =this.createExample(user);

        List<TbUser> list = userServiceImpl.list(queryWrapper);
        return new Result<List<TbUser>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable String id) {
        //调用UserService实现根据主键删除
        userServiceImpl.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody TbUser user, @PathVariable String id) {
        //设置主键值
        user.setUsername(id);
        //调用UserService实现修改User
        userServiceImpl.updateById(user);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @PostMapping
    public Result add(@RequestBody TbUser user) {
        //调用UserService实现添加User
        userServiceImpl.save(user);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbUser> findById(@PathVariable String id) {
        //调用UserService实现根据主键查询User
        TbUser user = userServiceImpl.getById(id);
        return new Result<TbUser>(true, StatusCode.OK, "查询成功", user);
    }

    /**
     * 加载用户的数据
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    public Result<TbUser> findByUsername(@PathVariable(name="id") String id) {
        //调用UserService实现根据主键查询User
        TbUser user = userServiceImpl.getById(id);
        return new Result<TbUser>(true, StatusCode.OK, "查询成功", user);
    }

    /***
     *
     * 希望 拥有admin的角色人才能访问.
     * 查询User全部数据
     * @return
     */
    // @PreAuthorize 表示 在执行方法之前 先进行权限校验,只有拥有 admin角色的用户可以执行该方法.
    @GetMapping
    public Result<List<TbUser>> findAll(HttpServletRequest request) {

        System.out.println("头信息为:"+request.getHeader("Authorization"));


        //调用UserService实现查询所有User
        List<TbUser> list = userServiceImpl.list();
        return new Result<List<TbUser>>(true, StatusCode.OK, "查询成功", list);
    }

    @RequestMapping("/login")
    public Result<TbUser> login(String username, String password, HttpServletResponse response, HttpServletRequest request) {
        //1.从数据库中查询用户名对应的用户的对象
        TbUser user = userServiceImpl.getById(username);
        if (user == null) {
            //2.判断用户是否为空 为空返回数据
            return new Result<TbUser>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }

        //3如果不为空格 判断 密码是否正确 正确则登录成功

        if(BCrypt.checkpw(password,user.getPassword())){
            //成功
            Map<String,Object> info = new HashMap<String,Object>();
            info.put("role","USER");
            info.put("success","SUCCESS");
            info.put("username",username);

            //1.生成令牌
            String jwt = JwtsUtils.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
            //2.设置cookie中
            Cookie cookie = new Cookie("Authorization",jwt);
            response.addCookie(cookie);
            //3.设置头文件中
            response.setHeader("Authorization",jwt);

            return new Result<TbUser>(true, StatusCode.OK, "成功",jwt);
        }else{
            //失败
            return new Result<TbUser>(false, StatusCode.LOGINERROR, "用户名或密码错误");
        }
    }



    /**
     * User构建查询对象
     * @param template
     * @return
     */
    private QueryWrapper< TbUser > createExample(TbUser template){
        QueryWrapper< TbUser > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getPassword()), TbUser::getPassword, template.getPassword())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getPhone()), TbUser::getPhone, template.getPhone())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getEmail()), TbUser::getEmail, template.getEmail())
                .eq(template.getCreated()!=null, TbUser::getCreated, template.getCreated())
                .eq(template.getUpdated()!=null, TbUser::getCreated, template.getCreated())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getStatus()), TbUser::getStatus, template.getStatus())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getHeadPic()), TbUser::getHeadPic, template.getHeadPic())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getQq()), TbUser::getQq, template.getQq())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getIsMobileCheck()), TbUser::getIsMobileCheck, template.getIsMobileCheck())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getIsEmailCheck()), TbUser::getIsEmailCheck, template.getIsEmailCheck())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getSourceType()), TbUser::getSourceType, template.getSourceType())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getNickName()), TbUser::getNickName, template.getNickName())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getName()), TbUser::getName, template.getName())
                .eq(template.getUserLevel()!=null, TbUser::getUserLevel, template.getUserLevel())
                .eq(template.getPoints()!=null, TbUser::getPoints, template.getPoints())
                .eq(template.getExperienceValue()!=null, TbUser::getExperienceValue, template.getExperienceValue())
                .eq(template.getBirthday()!=null, TbUser::getBirthday, template.getBirthday())
                .eq(template.getLastLoginTime()!=null, TbUser::getLastLoginTime, template.getLastLoginTime())
                .like(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getUsername()), TbUser::getUsername, template.getUsername())
                .like(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getName()), TbUser::getName, template.getName())
        ;
        return queryWrapper;
    }

}
