package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbSpec;
import com.me.goods.pojo.TbSpec;
import com.me.goods.service.impl.TbSpecServiceImpl;
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
@RequestMapping("/tb-spec")
public class TbSpecController {

    @Autowired
    private TbSpecServiceImpl specService;

    /***
     * Spec分页条件搜索实现
     * @param spec
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false) TbSpec spec, @PathVariable int page, @PathVariable  int size){
        //调用SpecService实现分页条件查询Spec
        QueryWrapper< TbSpec > queryWrapper =createExample( spec);

        Page< TbSpec > pagez = new Page<>(page, size);
        Page<TbSpec> pageInfo = specService.page(pagez,queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Spec分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SpecService实现分页查询Spec
        Page< TbSpec > pagez = new Page<>(page, size);
        Page<TbSpec> pageInfo = specService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spec
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbSpec> > findList(@RequestBody(required = false)  TbSpec spec){
        //调用SpecService实现条件查询Spec
        QueryWrapper< TbSpec > queryWrapper =createExample( spec);
        List<TbSpec> list = specService.list(queryWrapper);
        return new Result<List<TbSpec>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用SpecService实现根据主键删除
        specService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Spec数据
     * @param spec
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbSpec spec,@PathVariable Integer id){
        //设置主键值
        spec.setId(id);
        //调用SpecService实现修改Spec
        specService.updateById(spec);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Spec数据
     * @param spec
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbSpec spec){
        //调用SpecService实现添加Spec
        specService.save(spec);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Spec数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbSpec> findById(@PathVariable Integer id){
        //调用SpecService实现根据主键查询Spec

        TbSpec spec = specService.getById(id);
        return new Result<TbSpec>(true,StatusCode.OK,"查询成功",spec);
    }

    /***
     * 查询Spec全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbSpec>> findAll(){
        //调用SpecService实现查询所有Spec
        List<TbSpec> list = specService.list();
        return new Result<List<TbSpec>>(true, StatusCode.OK,"查询成功",list) ;
    }


    /**
     * 根据商品分类的ID 查询该分类对应的 规格的列表
     *
     */


//    @GetMapping("/category/{id}")
//    public Result<List<TbSpec>> findByCategoryId(@PathVariable(name="id") Integer id){
//        List<TbSpec> specList = specService.findByCategoryId(id);
//        return new Result<List<TbSpec>>(true,StatusCode.OK,"查询规格的列表成功",specList);
//    }

    /**
     * Album构建查询对象
     * @param spec
     * @return
     */
    private   QueryWrapper< TbSpec > createExample(TbSpec spec){
        QueryWrapper< TbSpec > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(spec.getId() != null, TbSpec::getId, spec.getId())
                .eq(spec.getSeq() != null, TbSpec::getSeq, spec.getSeq())
                .eq(spec.getTemplateId() != null, TbSpec::getTemplateId, spec.getTemplateId())
                .like(!StringUtils.isNullOrEmpty(spec.getName()), TbSpec::getName, spec.getName())
                .eq(!StringUtils.isNullOrEmpty(spec.getOptions()), TbSpec::getOptions, spec.getOptions())
        ;
        return queryWrapper;
    }
}
