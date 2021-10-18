package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.dto.Goods;
import com.me.goods.pojo.TbSpu;
import com.me.goods.pojo.TbSpu;
import com.me.goods.service.impl.TbSpuServiceImpl;
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
@Controller
@RequestMapping("/tb-spu")
public class TbSpuController {

    @Autowired
    private TbSpuServiceImpl spuService;

    /***
     * Spu分页条件搜索实现
     * @param spu
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@RequestBody(required = false)  TbSpu spu, @PathVariable int page, @PathVariable  int size){
        //调用SpuService实现分页条件查询Spu
        QueryWrapper< TbSpu > queryWrapper = createExample(spu);
        Page< TbSpu > pagez = new Page<>(page, size);
        Page< TbSpu > pageInfo = spuService.page(pagez,queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Spu分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SpuService实现分页查询Spu
        Page< TbSpu > pagez = new Page<>(page, size);
        Page<TbSpu> pageInfo = spuService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param spu
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbSpu> > findList(@RequestBody(required = false)  TbSpu spu){
        //调用SpuService实现条件查询Spu
        QueryWrapper< TbSpu > queryWrapper = createExample(spu);
        List<TbSpu> list = spuService.list(queryWrapper);
        return new Result<List<TbSpu>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SpuService实现根据主键删除
        spuService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Spu数据
     * @param spu
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbSpu spu,@PathVariable Long id){
        //设置主键值
        spu.setId(id);
        //调用SpuService实现修改Spu
        spuService.updateById(spu);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Spu数据
     * @param spu
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbSpu spu){
        //调用SpuService实现添加Spu
        spuService.save(spu);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbSpu> findById(@PathVariable Long id){
        //调用SpuService实现根据主键查询Spu
        TbSpu spu = spuService.getById(id);
        return new Result<TbSpu>(true,StatusCode.OK,"查询成功",spu);
    }

    /***
     * 查询Spu全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbSpu>> findAll(){
        //调用SpuService实现查询所有Spu
        List<TbSpu> list = spuService.list();
        return new Result<List<TbSpu>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * Goods(SPU+SKU)增加方法详情
     */
//    @PostMapping("/save")
//    public Result save(@RequestBody Goods goods){
//        spuService.save(goods);
//        return new Result(true,StatusCode.OK,"保存商品成功",null);
//    }
//
//    //根据点击到的商品(SPU)的ID 获取到GOODS数据返回给页面展示
//    @GetMapping("/goods/{id}")
//    public Result<Goods> findGoodsById(@PathVariable(value="id") Long id){
//        Goods goods = spuService.findGoodsById(id);
//        return new Result<Goods>(true,StatusCode.OK,"查询goods数据成功",goods);
//    }
//
//
//
//    /**
//     * //审核商品 自动上架
//     * @param id  spu的ID
//     * @return
//     */
//    @PutMapping("/audit/{id}")
//    public Result auditSpu(@PathVariable(name="id")Long id){
//        spuService.auditSpu(id);
//        return new Result(true,StatusCode.OK,"审核通过");
//    }
//
//    @PutMapping("/pull/{id}")
//    public Result pullSpu(@PathVariable(name="id")Long id){
//        spuService.pullSpu(id);
//        return new Result(true,StatusCode.OK,"下架成功");
//    }
//
//    @DeleteMapping("/logic/delete/{id}")
//    public Result logicDeleteSpu(@PathVariable(name="id")Long id){
//        spuService.logicDeleteSpu(id);
//        return new Result(true,StatusCode.OK,"逻辑删除成功");
//    }
//
//    @PutMapping("/restore/{id}")
//    public Result restore(@PathVariable(name="id")Long id){
//        spuService.restoreSpu(id);
//        return new Result(true,StatusCode.OK,"还原成功");
//    }


    /**
     * Album构建查询对象
     * @param spu
     * @return
     */
    private   QueryWrapper< TbSpu > createExample(TbSpu spu){
        QueryWrapper< TbSpu > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(spu.getId() != null, TbSpu::getId, spu.getId())
                .eq(spu.getBrandId() != null, TbSpu::getBrandId, spu.getBrandId())
                .eq(spu.getCategory1Id() != null, TbSpu::getCategory1Id, spu.getCategory1Id())
                .eq(spu.getCategory2Id() != null, TbSpu::getCategory2Id, spu.getCategory2Id())
                .eq(spu.getCategory3Id() != null, TbSpu::getCategory3Id, spu.getCategory3Id())
                .eq(spu.getCommentNum() != null, TbSpu::getCommentNum, spu.getCommentNum())
                .eq(spu.getFreightId() != null, TbSpu::getFreightId, spu.getFreightId())
                .eq(spu.getTemplateId() != null, TbSpu::getTemplateId, spu.getTemplateId())
                .like(!StringUtils.isNullOrEmpty(spu.getName()), TbSpu::getName, spu.getName())
                .eq(!StringUtils.isNullOrEmpty(spu.getCaption()), TbSpu::getCaption, spu.getCaption())
                .eq(!StringUtils.isNullOrEmpty(spu.getImage()), TbSpu::getImage, spu.getImage())
                .eq(!StringUtils.isNullOrEmpty(spu.getImages()), TbSpu::getImages, spu.getImages())
                .eq(!StringUtils.isNullOrEmpty(spu.getIntroduction()), TbSpu::getIntroduction, spu.getIntroduction())
                .eq(!StringUtils.isNullOrEmpty(spu.getIsEnableSpec()), TbSpu::getIsEnableSpec, spu.getIsEnableSpec())
                .eq(!StringUtils.isNullOrEmpty(spu.getIsMarketable()), TbSpu::getIsMarketable, spu.getIsMarketable())
                .eq(!StringUtils.isNullOrEmpty(spu.getParaItems()), TbSpu::getParaItems, spu.getParaItems())
                .eq(!StringUtils.isNullOrEmpty(spu.getSaleService()), TbSpu::getSaleService, spu.getSaleService())
                .eq(!StringUtils.isNullOrEmpty(spu.getSn()), TbSpu::getSn, spu.getSn())
        ;
        return queryWrapper;
    }

}
