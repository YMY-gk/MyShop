package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbPara;
import com.me.goods.pojo.TbPref;
import com.me.goods.service.impl.TbPrefServiceImpl;
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
@RequestMapping("/tb-pref")
public class TbPrefController {

    @Autowired
    private TbPrefServiceImpl prefService;

    /***
     * Pref分页条件搜索实现
     * @param pref
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false)  TbPref pref, @PathVariable int page, @PathVariable  int size){
        //调用PrefService实现分页条件查询Pref
        QueryWrapper< TbPref > queryWrapper = createExample(pref);

        Page< TbPref > pagez = new Page<>(page, size);
        Page< TbPref > pageInfo = prefService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Pref分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用PrefService实现分页查询Pref
        Page< TbPref > pagez = new Page<>(page, size);
        Page< TbPref > pageInfo = prefService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param pref
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbPref> > findList(@RequestBody(required = false)  TbPref pref){
        //调用PrefService实现条件查询Pref
        QueryWrapper< TbPref > queryWrapper = createExample(pref);
        List<TbPref> list = prefService.list(queryWrapper);
        return new Result<List<TbPref>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用PrefService实现根据主键删除
        prefService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Pref数据
     * @param pref
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbPref pref,@PathVariable Integer id){
        //设置主键值
        pref.setId(id);
        //调用PrefService实现修改Pref
        prefService.updateById(pref);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Pref数据
     * @param pref
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbPref pref){
        //调用PrefService实现添加Pref
        prefService.save(pref);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Pref数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbPref> findById(@PathVariable Integer id){
        //调用PrefService实现根据主键查询Pref
        TbPref pref = prefService.getById(id);
        return new Result<TbPref>(true,StatusCode.OK,"查询成功",pref);
    }

    /***
     * 查询Pref全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbPref>> findAll(){
        //调用PrefService实现查询所有Pref
        List<TbPref> list = prefService.list();
        return new Result<List<TbPref>>(true, StatusCode.OK,"查询成功",list) ;
    }

    public QueryWrapper createExample(TbPref pref){
        QueryWrapper< TbPref > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(!StringUtils.isNullOrEmpty(pref.getState()), TbPref::getState, pref.getState())
                .eq(!StringUtils.isNullOrEmpty(pref.getStartTime()), TbPref::getStartTime, pref.getStartTime())
                .eq(!StringUtils.isNullOrEmpty(pref.getEndTime()), TbPref::getEndTime, pref.getEndTime())
                .eq(!StringUtils.isNullOrEmpty(pref.getType()), TbPref::getType, pref.getType())
                .eq(pref.getId()!=null, TbPref::getId, pref.getId())
                .eq(pref.getCateId()!=null, TbPref::getCateId, pref.getCateId())
                .eq(pref.getBuyMoney()!=null, TbPref::getBuyMoney, pref.getBuyMoney())
                .eq(pref.getPreMoney()!=null, TbPref::getPreMoney, pref.getPreMoney())
        ;

        return queryWrapper;
    }
}
