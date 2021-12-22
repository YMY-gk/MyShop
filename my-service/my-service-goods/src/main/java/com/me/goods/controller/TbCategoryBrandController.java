package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbAlbum;
import com.me.goods.pojo.TbCategoryBrand;
import com.me.goods.service.impl.TbCategoryBrandServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/tb-category-brand")
public class TbCategoryBrandController {

    @Autowired
    private TbCategoryBrandServiceImpl categoryBrandService;

    /***
     * CategoryBrand分页条件搜索实现
     * @param categoryBrand
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result< Page > findPage(@RequestBody(required = false) TbCategoryBrand categoryBrand, @PathVariable int page, @PathVariable  int size){
        //调用CategoryBrandService实现分页条件查询CategoryBrand
        QueryWrapper< TbCategoryBrand > queryWrapper = createExample(categoryBrand);
        Page< TbCategoryBrand > pagez = new Page<>(page, size);
        Page<TbCategoryBrand> pageInfo = categoryBrandService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * CategoryBrand分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用CategoryBrandService实现分页查询CategoryBrand
        Page< TbCategoryBrand > pagez = new Page<>(page, size);
        Page<TbCategoryBrand> pageInfo = categoryBrandService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param categoryBrand
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbCategoryBrand> > findList(@RequestBody(required = false)  TbCategoryBrand categoryBrand){
        //调用CategoryBrandService实现条件查询CategoryBrand
        QueryWrapper< TbCategoryBrand > queryWrapper = createExample(categoryBrand);
        List<TbCategoryBrand> list = categoryBrandService.list(queryWrapper);
        return new Result<List<TbCategoryBrand>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用CategoryBrandService实现根据主键删除
        categoryBrandService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改CategoryBrand数据
     * @param categoryBrand
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbCategoryBrand categoryBrand,@PathVariable Integer id){
        //设置主键值
        categoryBrand.setCategoryId(id);
        //调用CategoryBrandService实现修改CategoryBrand
        categoryBrandService.updateById(categoryBrand);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增CategoryBrand数据
     * @param categoryBrand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbCategoryBrand categoryBrand){
        //调用CategoryBrandService实现添加CategoryBrand
        categoryBrandService.save(categoryBrand);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询CategoryBrand数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbCategoryBrand> findById(@PathVariable Integer id){
        //调用CategoryBrandService实现根据主键查询CategoryBrand
        TbCategoryBrand categoryBrand = categoryBrandService.getById(id);
        return new Result<TbCategoryBrand>(true,StatusCode.OK,"查询成功",categoryBrand);
    }

    /***
     * 查询CategoryBrand全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbCategoryBrand>> findAll(){
        //调用CategoryBrandService实现查询所有CategoryBrand
        List<TbCategoryBrand> list = categoryBrandService.list();
        return new Result<List<TbCategoryBrand>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * CategoryBrand构建查询对象
     * @param categoryBrand
     * @return
     */
    public QueryWrapper createExample(TbCategoryBrand categoryBrand){
        QueryWrapper< TbCategoryBrand > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(categoryBrand.getCategoryId() != null, TbCategoryBrand::getCategoryId, categoryBrand.getCategoryId())
                .eq(categoryBrand.getBrandId() != null, TbCategoryBrand::getBrandId, categoryBrand.getBrandId())
        ;
        return queryWrapper;
    }

}
