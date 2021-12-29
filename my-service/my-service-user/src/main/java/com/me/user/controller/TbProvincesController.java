package com.me.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.user.pojo.TbCities;
import com.me.user.pojo.TbProvinces;
import com.me.user.service.impl.TbProvincesServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 省份信息表 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Controller
@RequestMapping("/tb-provinces")
public class TbProvincesController {

    @Autowired
    private TbProvincesServiceImpl provincesService;

    /**
     * TbAddress构建查询对象
     * @param template
     * @return
     */
    private QueryWrapper< TbProvinces > createExample(TbProvinces template){
        QueryWrapper< TbProvinces > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!StringUtils.isNullOrEmpty(template.getProvinceid()), TbProvinces::getProvinceid, template.getProvinceid())
                .eq(!StringUtils.isNullOrEmpty(template.getProvince()), TbProvinces::getProvince, template.getProvince())
        ;
        return queryWrapper;
    }

    /***
     * Provinces分页条件搜索实现
     * @param provinces
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result< Page > findPage(@RequestBody(required = false) TbProvinces provinces, @PathVariable int page, @PathVariable  int size){
        //调用ProvincesService实现分页条件查询Provinces
        Page<TbProvinces> pagez = new Page<>(page,size);
        QueryWrapper< TbProvinces > queryWrapper =this.createExample(provinces);
        Page<TbProvinces> pageInfo = provincesService.page(pagez,queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Provinces分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ProvincesService实现分页查询Provinces
        Page<TbProvinces> pagez = new Page<>(page,size);
        Page<TbProvinces> pageInfo = provincesService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param provinces
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbProvinces> > findList(@RequestBody(required = false)  TbProvinces provinces){
        //调用ProvincesService实现条件查询Provinces
        QueryWrapper< TbProvinces > queryWrapper =this.createExample(provinces);

        List<TbProvinces> list = provincesService.list(queryWrapper);
        return new Result<List<TbProvinces>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用ProvincesService实现根据主键删除
        provincesService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Provinces数据
     * @param provinces
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbProvinces provinces,@PathVariable String id){
        //设置主键值
        provinces.setProvinceid(id);
        //调用ProvincesService实现修改Provinces
        provincesService.updateById(provinces);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Provinces数据
     * @param provinces
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbProvinces provinces){
        //调用ProvincesService实现添加Provinces
        provincesService.save(provinces);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Provinces数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbProvinces> findById(@PathVariable String id){
        //调用ProvincesService实现根据主键查询Provinces
        TbProvinces provinces = provincesService.getById(id);
        return new Result<TbProvinces>(true,StatusCode.OK,"查询成功",provinces);
    }

    /***
     * 查询Provinces全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbProvinces>> findAll(){
        //调用ProvincesService实现查询所有Provinces
        List<TbProvinces> list = provincesService.list();
        return new Result<List<TbProvinces>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
