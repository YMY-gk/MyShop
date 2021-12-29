package com.me.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.user.pojo.TbAddress;
import com.me.user.pojo.TbAreas;
import com.me.user.service.impl.TbAreasServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 行政区域县区信息表 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Controller
@RequestMapping("/tb-areas")
public class TbAreasController {
    @Autowired
    private TbAreasServiceImpl areasService;


    /**
     * TbAddress构建查询对象
     * @param template
     * @return
     */
    private QueryWrapper< TbAreas > createExample(TbAreas template){
        QueryWrapper< TbAreas > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!StringUtils.isNullOrEmpty(template.getCityid()), TbAreas::getCityid, template.getCityid())
                .eq(!StringUtils.isNullOrEmpty(template.getAreaid()), TbAreas::getAreaid, template.getAreaid())
                .eq(!StringUtils.isNullOrEmpty(template.getArea()), TbAreas::getArea, template.getArea())
        ;
        return queryWrapper;
    }
    /***
     * Areas分页条件搜索实现
     * @param areas
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false)  TbAreas areas, @PathVariable int page, @PathVariable  int size){
        //调用AreasService实现分页条件查询Areas
        Page<TbAreas> pagez = new Page<>(page,size);
        QueryWrapper< TbAreas > queryWrapper =this.createExample(areas);
        Page<TbAreas> pageInfo = areasService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Areas分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AreasService实现分页查询Areas
        Page<TbAreas> pagez = new Page<>(page,size);

        Page<TbAreas> pageInfo = areasService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param areas
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbAreas> > findList(@RequestBody(required = false)  TbAreas areas){
        //调用AreasService实现条件查询Areas
        QueryWrapper< TbAreas > queryWrapper =this.createExample(areas);

        List<TbAreas> list = areasService.list(queryWrapper);
        return new Result<List<TbAreas>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用AreasService实现根据主键删除
        areasService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Areas数据
     * @param areas
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbAreas areas,@PathVariable String id){
        //设置主键值
        areas.setAreaid(id);
        //调用AreasService实现修改Areas
        areasService.updateById(areas);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Areas数据
     * @param areas
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbAreas areas){
        //调用AreasService实现添加Areas
        areasService.save(areas);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Areas数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbAreas> findById(@PathVariable String id){
        //调用AreasService实现根据主键查询Areas
        TbAreas areas = areasService.getById(id);
        return new Result<TbAreas>(true,StatusCode.OK,"查询成功",areas);
    }

    /***
     * 查询Areas全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbAreas>> findAll(){
        //调用AreasService实现查询所有Areas
        List<TbAreas> list = areasService.list();
        return new Result<List<TbAreas>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
