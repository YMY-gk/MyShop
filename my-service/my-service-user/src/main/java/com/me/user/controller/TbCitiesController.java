package com.me.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.user.pojo.TbAreas;
import com.me.user.pojo.TbCities;
import com.me.user.service.impl.TbCitiesServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 行政区域地州市信息表 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Controller
@RequestMapping("/tb-cities")
public class TbCitiesController {
    @Autowired
    private TbCitiesServiceImpl citiesService;

    /**
     * TbAddress构建查询对象
     * @param template
     * @return
     */
    private QueryWrapper< TbCities > createExample(TbCities template){
        QueryWrapper< TbCities > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!StringUtils.isNullOrEmpty(template.getCityid()), TbCities::getCityid, template.getCityid())
                .eq(!StringUtils.isNullOrEmpty(template.getCity()), TbCities::getCity, template.getCity())
                .eq(!StringUtils.isNullOrEmpty(template.getProvinceid()), TbCities::getProvinceid, template.getProvinceid())
        ;
        return queryWrapper;
    }

    /***
     * Cities分页条件搜索实现
     * @param cities
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false)  TbCities cities, @PathVariable int page, @PathVariable  int size){
        //调用CitiesService实现分页条件查询Cities
        Page<TbCities> pagez = new Page<>(page,size);
        QueryWrapper< TbCities > queryWrapper =this.createExample(cities);
        Page<TbCities> pageInfo = citiesService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Cities分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CitiesService实现分页查询Cities
        Page<TbCities> pagez = new Page<>(page,size);
        Page<TbCities> pageInfo = citiesService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param cities
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbCities> > findList(@RequestBody(required = false)  TbCities cities){
        //调用CitiesService实现条件查询Cities
        QueryWrapper< TbCities > queryWrapper =this.createExample(cities);
        List<TbCities> list = citiesService.list(queryWrapper);
        return new Result<List<TbCities>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用CitiesService实现根据主键删除
        citiesService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Cities数据
     * @param cities
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbCities cities,@PathVariable String id){
        //设置主键值
        cities.setCityid(id);
        //调用CitiesService实现修改Cities
        citiesService.updateById(cities);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Cities数据
     * @param cities
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbCities cities){
        //调用CitiesService实现添加Cities
        citiesService.save(cities);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Cities数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbCities> findById(@PathVariable String id){
        //调用CitiesService实现根据主键查询Cities
        TbCities cities = citiesService.getById(id);
        return new Result<TbCities>(true,StatusCode.OK,"查询成功",cities);
    }

    /***
     * 查询Cities全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbCities>> findAll(){
        //调用CitiesService实现查询所有Cities
        List<TbCities> list = citiesService.list();
        return new Result<List<TbCities>>(true, StatusCode.OK,"查询成功",list) ;
    }


}
