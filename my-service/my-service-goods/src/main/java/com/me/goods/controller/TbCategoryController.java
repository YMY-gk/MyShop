package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbBrand;
import com.me.goods.pojo.TbCategory;
import com.me.goods.pojo.TbCategoryBrand;
import com.me.goods.service.impl.TbCategoryServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Controller
@RequestMapping("/tb-category")
public class TbCategoryController {

    @Autowired
    private TbCategoryServiceImpl categoryService;

    /***
     * Category分页条件搜索实现
     * @param category
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result< Page > findPage(@RequestBody(required = false) TbCategory category, @PathVariable int page, @PathVariable int size) {
        //调用CategoryService实现分页条件查询Category
        QueryWrapper< TbCategory > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isNullOrEmpty(category.getName()), TbCategory::getName, category.getName())
        ;
        Page< TbCategory > pagez = new Page<>(page, size);
        Page< TbCategory > pageInfo = categoryService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Category分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result< Page > findPage(@PathVariable int page, @PathVariable int size) {
        //调用CategoryService实现分页查询Category
        Page< TbCategory > pagez = new Page<>(page, size);
        Page< TbCategory > pageInfo = categoryService.page(pagez);
        return new Result< Page >(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param category
     * @return
     */
    @PostMapping(value = "/search")
    public Result< List< TbCategory > > findList(@RequestBody(required = false) TbCategory category) {
        //调用CategoryService实现条件查询Category
        QueryWrapper< TbCategory > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(!StringUtils.isNullOrEmpty(category.getName()), TbCategory::getName, category.getName())
        ;
        List< TbCategory > list = categoryService.list(queryWrapper);
        return new Result< List< TbCategory > >(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用CategoryService实现根据主键删除
        categoryService.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Category数据
     * @param category
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody TbCategory category, @PathVariable Integer id) {
        //设置主键值
        category.setId(id);
        //调用CategoryService实现修改Category
        categoryService.updateById(category);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Category数据
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody TbCategory category) {
        //调用CategoryService实现添加Category
        categoryService.save(category);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Category数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result< TbCategory > findById(@PathVariable Integer id) {
        //调用CategoryService实现根据主键查询Category
        TbCategory category = categoryService.getById(id);
        return new Result< TbCategory >(true, StatusCode.OK, "查询成功", category);
    }

    /***
     * 查询Category全部数据
     * @return
     */
    @GetMapping
    public Result< List< TbCategory > > findAll() {
        //调用CategoryService实现查询所有Category
        List< TbCategory > list = categoryService.list();
        return new Result< List< TbCategory > >(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据父ID 查询该分类下的所有的子分类列表   如果是一级分类 pid = 0
     *
     * @param pid
     * @return
     */
    @GetMapping("/list/{pid}")
    public Result< List< TbCategory > > findByParentId(@PathVariable(name = "pid") Integer pid) {
        //SELECT * from tb_category where parent_id=
        QueryWrapper< TbCategory > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(pid != null, TbCategory::getParentId, pid)
        ;
        List< TbCategory > categoryList = categoryService.list(queryWrapper);
        return new Result< List< TbCategory > >(true, StatusCode.OK, "查询分类列表成功", categoryList);
    }
}
