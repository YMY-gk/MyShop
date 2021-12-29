package com.me.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.me.user.pojo.OauthClientDetails;
import com.me.user.service.impl.OauthClientDetailsServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/oauthClientDetails")
@CrossOrigin
public class OauthClientDetailsController {

    @Autowired
    private OauthClientDetailsServiceImpl oauthClientDetailsService;

    /**
     * User构建查询对象
     * @param template
     * @return
     */
    private QueryWrapper< OauthClientDetails > createExample(OauthClientDetails template){
        QueryWrapper< OauthClientDetails > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getClientId()), OauthClientDetails::getClientId, template.getClientId())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getResourceIds()), OauthClientDetails::getResourceIds, template.getResourceIds())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getClientSecret()), OauthClientDetails::getClientSecret, template.getClientSecret())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getScope()), OauthClientDetails::getScope, template.getScope())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getAuthorizedGrantTypes()), OauthClientDetails::getAuthorizedGrantTypes, template.getAuthorizedGrantTypes())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getWebServerRedirectUri()), OauthClientDetails::getWebServerRedirectUri, template.getWebServerRedirectUri())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getAuthorities()), OauthClientDetails::getAuthorities, template.getAuthorities())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getAdditionalInformation()), OauthClientDetails::getAdditionalInformation, template.getAdditionalInformation())
                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(template.getAutoapprove()), OauthClientDetails::getAutoapprove, template.getAutoapprove())
                .eq(template.getRefreshTokenValidity()!=null, OauthClientDetails::getRefreshTokenValidity, template.getRefreshTokenValidity())

        ;
        return queryWrapper;
    }
    /***
     * OauthClientDetails分页条件搜索实现
     * @param oauthClientDetails
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false) OauthClientDetails oauthClientDetails, @PathVariable  int page, @PathVariable  int size){
        //调用OauthClientDetailsService实现分页条件查询OauthClientDetails
        Page< OauthClientDetails > pagez = new Page<>(page,size);
        QueryWrapper< OauthClientDetails > queryWrapper =this.createExample(oauthClientDetails);
        Page<OauthClientDetails> pageInfo = oauthClientDetailsService.page(pagez, queryWrapper);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * OauthClientDetails分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用OauthClientDetailsService实现分页查询OauthClientDetails
        Page< OauthClientDetails > pagez = new Page<>(page,size);
        Page<OauthClientDetails> pageInfo = oauthClientDetailsService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param oauthClientDetails
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<OauthClientDetails>> findList(@RequestBody(required = false)  OauthClientDetails oauthClientDetails){
        //调用OauthClientDetailsService实现条件查询OauthClientDetails
        QueryWrapper< OauthClientDetails > queryWrapper =this.createExample(oauthClientDetails);

        List<OauthClientDetails> list = oauthClientDetailsService.list(queryWrapper);
        return new Result<List<OauthClientDetails>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用OauthClientDetailsService实现根据主键删除
        oauthClientDetailsService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改OauthClientDetails数据
     * @param oauthClientDetails
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  OauthClientDetails oauthClientDetails,@PathVariable String id){
        //设置主键值
        oauthClientDetails.setClientId(id);
        //调用OauthClientDetailsService实现修改OauthClientDetails
        oauthClientDetailsService.updateById(oauthClientDetails);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增OauthClientDetails数据
     * @param oauthClientDetails
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   OauthClientDetails oauthClientDetails){
        //调用OauthClientDetailsService实现添加OauthClientDetails
        oauthClientDetailsService.save(oauthClientDetails);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询OauthClientDetails数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<OauthClientDetails> findById(@PathVariable String id){
        //调用OauthClientDetailsService实现根据主键查询OauthClientDetails
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getById(id);
        return new Result<OauthClientDetails>(true,StatusCode.OK,"查询成功",oauthClientDetails);
    }

    /***
     * 查询OauthClientDetails全部数据
     * @return
     */
    @GetMapping
    public Result<List<OauthClientDetails>> findAll(){
        //调用OauthClientDetailsService实现查询所有OauthClientDetails
        List<OauthClientDetails> list = oauthClientDetailsService.list();
        return new Result<List<OauthClientDetails>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
