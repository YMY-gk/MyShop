package com.me.content.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.content.pojo.TbContentCategory;
import com.me.content.service.impl.TbContentCategoryServiceImpl;
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
@RequestMapping("/contentCategory")
@CrossOrigin
public class ContentCategoryController {

//    @Autowired
//    private TbContentCategoryServiceImpl contentCategoryService;
//
//    /***
//     * ContentCategory分页条件搜索实现
//     * @param contentCategory
//     * @param page
//     * @param size
//     * @return
//     */
//    @PostMapping(value = "/search/{page}/{size}" )
//    public Result<Page> findPage(@RequestBody(required = false) TbContentCategory contentCategory, @PathVariable  int page, @PathVariable  int size){
//        //调用ContentCategoryService实现分页条件查询ContentCategory
//        QueryWrapper< TbContentCategory > queryWrapper = createExample(contentCategory);
//        Page< TbContentCategory > pagez = new Page<>(page, size);
//        Page<TbContentCategory> pageInfo = contentCategoryService.page(pagez,queryWrapper);
//        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * ContentCategory分页搜索实现
//     * @param page:当前页
//     * @param size:每页显示多少条
//     * @return
//     */
//    @GetMapping(value = "/search/{page}/{size}" )
//    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
//        //调用ContentCategoryService实现分页查询ContentCategory
//        Page< TbContentCategory > pagez = new Page<>(page, size);
//        Page<TbContentCategory> pageInfo = contentCategoryService.page(pagez);
//        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * 多条件搜索品牌数据
//     * @param contentCategory
//     * @return
//     */
//    @PostMapping(value = "/search" )
//    public Result<List<TbContentCategory>> findList(@RequestBody(required = false)  TbContentCategory contentCategory){
//        //调用ContentCategoryService实现条件查询ContentCategory
//        QueryWrapper< TbContentCategory > queryWrapper = createExample(contentCategory);
//        List<TbContentCategory> list = contentCategoryService.list(queryWrapper);
//        return new Result<List<TbContentCategory>>(true,StatusCode.OK,"查询成功",list);
//    }
//
//    /***
//     * 根据ID删除品牌数据
//     * @param id
//     * @return
//     */
//    @DeleteMapping(value = "/{id}" )
//    public Result delete(@PathVariable Long id){
//        //调用ContentCategoryService实现根据主键删除
//        contentCategoryService.removeById(id);
//        return new Result(true,StatusCode.OK,"删除成功");
//    }
//
//    /***
//     * 修改ContentCategory数据
//     * @param contentCategory
//     * @param id
//     * @return
//     */
//    @PutMapping(value="/{id}")
//    public Result update(@RequestBody  TbContentCategory contentCategory,@PathVariable Long id){
//        //设置主键值
//        contentCategory.setId(id);
//        //调用ContentCategoryService实现修改ContentCategory
//        contentCategoryService.updateById(contentCategory);
//        return new Result(true,StatusCode.OK,"修改成功");
//    }
//
//    /***
//     * 新增ContentCategory数据
//     * @param contentCategory
//     * @return
//     */
//    @PostMapping
//    public Result add(@RequestBody   TbContentCategory contentCategory){
//        //调用ContentCategoryService实现添加ContentCategory
//        contentCategoryService.save(contentCategory);
//        return new Result(true,StatusCode.OK,"添加成功");
//    }
//
//    /***
//     * 根据ID查询ContentCategory数据
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public Result<TbContentCategory> findById(@PathVariable Long id){
//        //调用ContentCategoryService实现根据主键查询ContentCategory
//        TbContentCategory contentCategory = contentCategoryService.getById(id);
//        return new Result<TbContentCategory>(true,StatusCode.OK,"查询成功",contentCategory);
//    }
//
//    /***
//     * 查询ContentCategory全部数据
//     * @return
//     */
//    @GetMapping
//    public Result<List<TbContentCategory>> findAll(){
//        //调用ContentCategoryService实现查询所有ContentCategory
//        List<TbContentCategory> list = contentCategoryService.list();
//        return new Result<List<TbContentCategory>>(true, StatusCode.OK,"查询成功",list) ;
//    }
//    /**
//     * ContentCategory构建查询对象
//     * @param contentCategory
//     * @return
//     */
//    public QueryWrapper createExample(TbContentCategory contentCategory){
//        QueryWrapper< TbContentCategory > queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda()
//                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(contentCategory.getName()), TbContentCategory::getName, contentCategory.getName())
//                .eq(contentCategory.getId()!=null, TbContentCategory::getId, contentCategory.getId())
//        ;
//        return queryWrapper;
//    }
}
