package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbAlbum;
import com.me.goods.pojo.TbBrand;
import com.me.goods.service.impl.TbBrandServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class TbBrandController {
    @Autowired
    private TbBrandServiceImpl  brandService;


    /**
     * url:/brand
     * 参数: 没有
     * 返回值: json   result<List<Brand>>
     *
     * @return
     */
    @GetMapping("list")
    public Result< List< TbBrand > > findAll() {

        List<TbBrand> all = brandService.list();

        return new Result<List<TbBrand>>(true, StatusCode.OK, "查询成功", all);
    }



    /**
     *  //根据品牌的主键查询品牌的数据返回
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbBrand>  findById(@PathVariable(name="id") Integer id){
        TbBrand brand =   brandService.getById(id);
        return new Result<TbBrand>(true,StatusCode.OK,"查询品牌成功",brand);
    }

    /**
     * 添加品牌数据
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody TbBrand brand){
        brandService.save(brand);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }

    /**
     *  根据ID 更新品牌的数据
     * @param brand 请求体 更新后的数据
     * @param id 要修改的品牌的ID
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody TbBrand brand,@PathVariable(value="id")Integer id){
        brand.setId(id);
        brandService.updateById(brand);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable(value="id") Integer id){
        brandService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     *
     * @param brand
     * @return
     */
    @PostMapping("/search")
    public Result<List<TbBrand>> findList(@RequestBody  TbBrand brand){
        QueryWrapper<TbBrand> queryWrapper =createExample(brand);
        List<TbBrand> brands =  brandService.list(queryWrapper);
        return new Result(true,StatusCode.OK,"条件查询成功",brands);
    }

    /**
     *  分页查询
     * @param page 当前页
     * @param size 每页显示的行
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result< Page<TbBrand> > findPage(@PathVariable(value="page")Integer page, @PathVariable(value="size") Integer size){
        Page<TbBrand> pagez = new Page<>(page,size);
        Page<TbBrand> info = brandService.page(pagez);
        return new Result<Page<TbBrand>>(true,StatusCode.OK,"分页查询成功",info);
    }

    /**
     * 根据条件 来分页查询
     * @param page
     * @param size
     * @param brand 添加对象(请求体)
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<Page<TbBrand>> findPage(@PathVariable(value="page")Integer page,@PathVariable(value="size") Integer size,@RequestBody  TbBrand brand){
        QueryWrapper<TbBrand> queryWrapper =createExample(brand);
        Page<TbBrand> pagez = new Page<>(page,size);
        Page<TbBrand> info = brandService.page(pagez,queryWrapper);
        return new Result<Page<TbBrand>>(true,StatusCode.OK,"分页查询成功",info);
    }
    /**
     * Album构建查询对象
     * @param brand
     * @return
     */
    private   QueryWrapper< TbBrand > createExample(TbBrand brand){
        QueryWrapper< TbBrand > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(brand.getId() != null, TbBrand::getId, brand.getId())
                .like(!StringUtils.isNullOrEmpty(brand.getName()), TbBrand::getName, brand.getName())
                .eq(!StringUtils.isNullOrEmpty(brand.getImage()), TbBrand::getImage, brand.getImage())
                .eq(!StringUtils.isNullOrEmpty(brand.getLetter()), TbBrand::getLetter, brand.getLetter())
                .eq(brand.getSeq()!=null, TbBrand::getSeq, brand.getSeq())
        ;
        return queryWrapper;
    }
}
