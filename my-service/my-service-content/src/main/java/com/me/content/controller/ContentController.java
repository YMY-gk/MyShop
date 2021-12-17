package com.me.content.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.content.pojo.TbContent;
import com.me.content.pojo.TbContentCategory;
import com.me.content.service.impl.TbContentServiceImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/content")
@CrossOrigin
public class ContentController {
//
//    @Autowired
//    private TbContentServiceImpl contentService;
//
//    /***
//     * Content分页条件搜索实现
//     * @param content
//     * @param page
//     * @param size
//     * @return
//     */
//    @PostMapping(value = "/search/{page}/{size}" )
//    public Result<Page> findPage(@RequestBody(required = false) TbContent content, @PathVariable  int page, @PathVariable  int size){
//        //调用ContentService实现分页条件查询Content
//        QueryWrapper< TbContent > queryWrapper = createExample(content);
//        Page< TbContent > pagez = new Page<>(page, size);
//        Page<TbContent> pageInfo = contentService.page(pagez,queryWrapper);
//        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * Content分页搜索实现
//     * @param page:当前页
//     * @param size:每页显示多少条
//     * @return
//     */
//    @GetMapping(value = "/search/{page}/{size}" )
//    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
//        //调用ContentService实现分页查询Content
//        Page< TbContent > pagez = new Page<>(page, size);
//        Page<TbContent> pageInfo = contentService.page(pagez);
//        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
//    }
//
//    /***
//     * 多条件搜索品牌数据
//     * @param content
//     * @return
//     */
//    @PostMapping(value = "/search" )
//    public Result<List<TbContent>> findList(@RequestBody(required = false)  TbContent content){
//        //调用ContentService实现条件查询Content
//        QueryWrapper< TbContent > queryWrapper = createExample(content);
//
//        List<TbContent> list = contentService.list(queryWrapper);
//        return new Result<List<TbContent>>(true,StatusCode.OK,"查询成功",list);
//    }
//
//    /***
//     * 根据ID删除品牌数据
//     * @param id
//     * @return
//     */
//    @DeleteMapping(value = "/{id}" )
//    public Result delete(@PathVariable Long id){
//        //调用ContentService实现根据主键删除
//        contentService.removeById(id);
//        return new Result(true,StatusCode.OK,"删除成功");
//    }
//
//    /***
//     * 修改Content数据
//     * @param content
//     * @param id
//     * @return
//     */
//    @PutMapping(value="/{id}")
//    public Result update(@RequestBody  TbContent content,@PathVariable Long id){
//        //设置主键值
//        content.setId(id);
//        //调用ContentService实现修改Content
//        contentService.updateById(content);
//        return new Result(true,StatusCode.OK,"修改成功");
//    }
//
//    /***
//     * 新增Content数据
//     * @param content
//     * @return
//     */
//    @PostMapping
//    public Result add(@RequestBody   TbContent content){
//        //调用ContentService实现添加Content
//        contentService.save(content);
//        return new Result(true,StatusCode.OK,"添加成功");
//    }
//
//    /***
//     * 根据ID查询Content数据
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public Result<TbContent> findById(@PathVariable Long id){
//        //调用ContentService实现根据主键查询Content
//        TbContent content = contentService.getById(id);
//        return new Result<TbContent>(true,StatusCode.OK,"查询成功",content);
//    }
//
//    /***
//     * 查询Content全部数据
//     * @return
//     */
//    @GetMapping
//    public Result<List<TbContent>> findAll(){
//        //调用ContentService实现查询所有Content
//        List<TbContent> list = contentService.list();
//        return new Result<List<TbContent>>(true, StatusCode.OK,"查询成功",list) ;
//    }
//
//
//    //根据分类的ID 获取该分类下的所有的广告的列表
//
//    @GetMapping(value = "/list/category/{id}")
//    public Result<List<TbContent>> findByCategory(@PathVariable(name="id") Long id){
//        TbContent content = new TbContent();
//        content.setCategoryId(id);
//        content.setStatus(1);
//        QueryWrapper< TbContent > queryWrapper = createExample(content);
//        List<TbContent>  contents = contentService.list(queryWrapper);
//       return new Result<>(true,StatusCode.OK,"查询成功 ",contents);
//    }
//    /**
//     * ContentCategory构建查询对象
//     * @param content
//     * @return
//     */
//    public QueryWrapper createExample(TbContent content){
//        QueryWrapper< TbContent > queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda()
//                .like(!com.mysql.cj.util.StringUtils.isNullOrEmpty(content.getTitle()), TbContent::getTitle, content.getTitle())
//                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(content.getUrl()), TbContent::getUrl, content.getUrl())
//                .eq(!com.mysql.cj.util.StringUtils.isNullOrEmpty(content.getPic()), TbContent::getPic, content.getPic())
//                .eq(content.getStatus()!=null, TbContent::getStatus, content.getStatus())
//                .eq(content.getSortorder()!=null, TbContent::getSortorder, content.getSortorder())
//                .eq(content.getId()!=null, TbContent::getId, content.getId())
//                .eq(content.getCategoryId()!=null, TbContent::getCategoryId, content.getCategoryId())
//        ;
//        return queryWrapper;
//    }
}
