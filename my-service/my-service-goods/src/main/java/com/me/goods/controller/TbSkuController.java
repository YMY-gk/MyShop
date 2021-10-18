package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbSku;
import com.me.goods.pojo.TbSpec;
import com.me.goods.pojo.TbSku;
import com.me.goods.service.impl.TbSkuServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Controller
@RequestMapping("/tb-sku")
public class TbSkuController {

    @Autowired
    private TbSkuServiceImpl skuService;

    /***
     * Sku分页条件搜索实现
     * @param sku
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false) TbSku sku, @PathVariable int page, @PathVariable  int size){
        //调用SkuService实现分页条件查询Sku
        QueryWrapper< TbSku > queryWrapper = createExample(sku);

        Page< TbSku > pagez = new Page<>(page, size);
        Page<TbSku> pageInfo = skuService.page(pagez,queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Sku分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SkuService实现分页查询Sku
        Page< TbSku > pagez = new Page<>(page, size);
        Page<TbSku> pageInfo = skuService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbSku> > findList(@RequestBody(required = false)  TbSku sku){
        //调用SkuService实现条件查询Sku
        QueryWrapper< TbSku > queryWrapper = createExample(sku);
        List<TbSku> list = skuService.list(queryWrapper);
        return new Result<List<TbSku>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SkuService实现根据主键删除
        skuService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Sku数据
     * @param sku
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbSku sku,@PathVariable Long id){
        //设置主键值
        sku.setId(id);
        //调用SkuService实现修改Sku
        skuService.updateById(sku);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Sku数据
     * @param sku
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbSku sku){
        //调用SkuService实现添加Sku
        skuService.save(sku);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbSku> findById(@PathVariable Long id){
        //调用SkuService实现根据主键查询Sku
        TbSku sku = skuService.getById(id);
        return new Result<TbSku>(true,StatusCode.OK,"查询成功",sku);
    }

    /***
     * 查询Sku全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbSku>> findAll(){
        //调用SkuService实现查询所有Sku
        List<TbSku> list = skuService.list();
        return new Result<List<TbSku>>(true, StatusCode.OK,"查询成功",list) ;
    }
    /**
     * Album构建查询对象
     * @param sku
     * @return
     */
    private   QueryWrapper< TbSku > createExample(TbSku sku){
        QueryWrapper< TbSku > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(sku.getId() != null, TbSku::getId, sku.getId())
                .eq(sku.getSpuId() != null, TbSku::getSpuId, sku.getSpuId())
                .eq(sku.getAlertNum() != null, TbSku::getAlertNum, sku.getAlertNum())
                .eq(sku.getCategoryId() != null, TbSku::getCategoryId, sku.getCategoryId())
                .eq(sku.getNum() != null, TbSku::getNum, sku.getNum())
                .eq(sku.getCommentNum() != null, TbSku::getCommentNum, sku.getCommentNum())
                .eq(sku.getSaleNum() != null, TbSku::getSaleNum, sku.getSaleNum())
                .eq(sku.getVersion() != null, TbSku::getVersion, sku.getVersion())
                .eq(sku.getWeight() != null, TbSku::getWeight, sku.getWeight())
                .eq(sku.getCreateTime() != null, TbSku::getCreateTime, sku.getCreateTime())
                .eq(sku.getUpdateTime() != null, TbSku::getUpdateTime, sku.getUpdateTime())
                .like(!StringUtils.isNullOrEmpty(sku.getName()), TbSku::getName, sku.getName())
                .eq(!StringUtils.isNullOrEmpty(sku.getBrandName()), TbSku::getBrandName, sku.getBrandName())
                .eq(!StringUtils.isNullOrEmpty(sku.getImage()), TbSku::getImage, sku.getImage())
                .eq(!StringUtils.isNullOrEmpty(sku.getImages()), TbSku::getImages, sku.getImages())
                .eq(!StringUtils.isNullOrEmpty(sku.getCategoryName()), TbSku::getCategoryName, sku.getCategoryName())
                .eq(!StringUtils.isNullOrEmpty(sku.getSpec()), TbSku::getSpec, sku.getSpec())
                .eq(!StringUtils.isNullOrEmpty(sku.getStatus()), TbSku::getStatus, sku.getStatus())
                .eq(!StringUtils.isNullOrEmpty(sku.getSn()), TbSku::getSn, sku.getSn())
        ;
        return queryWrapper;
    }
}
