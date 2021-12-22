package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbTemplate;
import com.me.goods.pojo.UndoLog;
import com.me.goods.service.impl.TbTemplateServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tb-template")
public class TbTemplateController {

    @Autowired
    private TbTemplateServiceImpl templateService;

    /***
     * Template分页条件搜索实现
     * @param template
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false) TbTemplate template, @PathVariable int page, @PathVariable  int size){
        //调用TemplateService实现分页条件查询Template
        QueryWrapper< TbTemplate > queryWrapper = createExample(template);
        Page< TbTemplate > pagez = new Page<>(page, size);
        Page<TbTemplate> pageInfo = templateService.page(pagez,queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Template分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用TemplateService实现分页查询Template
        Page< TbTemplate > pagez = new Page<>(page, size);
        Page<TbTemplate> pageInfo = templateService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param template
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbTemplate> > findList(@RequestBody(required = false)  TbTemplate template){
        //调用TemplateService实现条件查询Template
        QueryWrapper< TbTemplate > queryWrapper = createExample(template);

        List<TbTemplate> list = templateService.list(queryWrapper);
        return new Result<List<TbTemplate>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用TemplateService实现根据主键删除
        templateService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Template数据
     * @param template
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbTemplate template,@PathVariable Integer id){
        //设置主键值
        template.setId(id);
        //调用TemplateService实现修改Template
        templateService.updateById(template);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Template数据
     * @param template
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbTemplate template){
        //调用TemplateService实现添加Template
        templateService.save(template);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Template数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbTemplate> findById(@PathVariable Integer id){
        //调用TemplateService实现根据主键查询Template
        TbTemplate template = templateService.getById(id);
        return new Result<TbTemplate>(true,StatusCode.OK,"查询成功",template);
    }

    /***
     * 查询Template全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbTemplate>> findAll(){
        //调用TemplateService实现查询所有Template
        List<TbTemplate> list = templateService.list();
        return new Result<List<TbTemplate>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * Album构建查询对象
     * @param template
     * @return
     */
    private   QueryWrapper< TbTemplate > createExample(TbTemplate template){
        QueryWrapper< TbTemplate > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(template.getId() != null, TbTemplate::getId, template.getId())
                .eq(template.getParaNum() != null, TbTemplate::getParaNum, template.getParaNum())
                .eq(template.getSpecNum() != null, TbTemplate::getSpecNum, template.getSpecNum())
                .like(!StringUtils.isNullOrEmpty(template.getName()), TbTemplate::getName, template.getName())
        ;
        return queryWrapper;
    }
}
