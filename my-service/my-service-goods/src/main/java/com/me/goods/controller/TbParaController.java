package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbCategory;
import com.me.goods.pojo.TbPara;
import com.me.goods.service.impl.TbCategoryServiceImpl;
import com.me.goods.service.impl.TbParaServiceImpl;
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
@RequestMapping("/tb-para")
public class TbParaController {

    @Autowired
    private TbParaServiceImpl paraService;

    @Autowired
    private TbCategoryServiceImpl categoryService;
    /***
     * Para分页条件搜索实现
     * @param para
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result< Page > findPage(@RequestBody(required = false) TbPara para, @PathVariable int page, @PathVariable int size) {
        //调用ParaService实现分页条件查询Para
        QueryWrapper< TbPara > queryWrapper = createExample(para);
        Page< TbPara > pagez = new Page<>(page, size);
        Page<TbPara> pageInfo = paraService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Para分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<Page> findPage(@PathVariable int page, @PathVariable int size) {
        //调用ParaService实现分页查询Para
        Page< TbPara > pagez = new Page<>(page, size);
        Page<TbPara> pageInfo = paraService.page(pagez);
        return new Result<Page>(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param para
     * @return
     */
    @PostMapping(value = "/search")
    public Result< List<TbPara> > findList(@RequestBody(required = false) TbPara para) {
        //调用ParaService实现条件查询Para
        QueryWrapper< TbPara > queryWrapper = createExample(para);
        List<TbPara> list = paraService.list(queryWrapper);
        return new Result<List<TbPara>>(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        //调用ParaService实现根据主键删除
        paraService.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Para数据
     * @param para
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody TbPara para, @PathVariable Integer id) {
        //设置主键值
        para.setId(id);
        //调用ParaService实现修改Para
        paraService.updateById(para);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Para数据
     * @param para
     * @return
     */
    @PostMapping
    public Result add(@RequestBody TbPara para) {
        //调用ParaService实现添加Para
        paraService.save(para);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Para数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbPara> findById(@PathVariable Integer id) {
        //调用ParaService实现根据主键查询Para
        TbPara para = paraService.getById(id);
        return new Result<TbPara>(true, StatusCode.OK, "查询成功", para);
    }

    /***
     * 查询Para全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbPara>> findAll() {
        //调用ParaService实现查询所有Para
        List<TbPara> list = paraService.list();
        return new Result<List<TbPara>>(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据3 商品的分类的ID 查询该三级分类对应的参数的列表
     *
     * @param id
     * @return
     */
    @GetMapping("/category/{id}")
    public Result<List<TbPara>> findParaByCateogryId(@PathVariable(name = "id") Integer id) {
        TbCategory category =categoryService.getById(id);
        QueryWrapper< TbPara > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(category != null, TbPara::getTemplateId, category.getTemplateId())
        ;
        List<TbPara> list = paraService.list(queryWrapper);
        return new Result<List<TbPara>>(true, StatusCode.OK, "参数列表查询成功", list);
    }
    /**
     * Para构建查询对象
     *
     * @param para
     * @return
     */
    public QueryWrapper createExample(TbPara para) {
        QueryWrapper< TbPara > queryWrapper = new QueryWrapper<>();
        if (para != null) {
            queryWrapper.lambda()
                    .eq(para.getId() != null, TbPara::getId, para.getId())
                    .eq(para.getSeq() != null, TbPara::getSeq, para.getSeq())
                    .eq(para.getTemplateId() != null, TbPara::getTemplateId, para.getTemplateId())
                    .eq(!StringUtils.isNullOrEmpty(para.getOptions()), TbPara::getOptions, para.getOptions())
                    .like(!StringUtils.isNullOrEmpty(para.getName()), TbPara::getName, para.getName())
            ;
        }
        return queryWrapper;
    }

}
