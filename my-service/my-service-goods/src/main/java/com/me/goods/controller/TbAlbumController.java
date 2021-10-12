package com.me.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.goods.pojo.TbAlbum;
import com.me.goods.pojo.TbBrand;
import com.me.goods.service.impl.TbAlbumServiceImpl;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author guokui
 * @since 2021-10-11
 */
@Controller
@RequestMapping("/album")
public class TbAlbumController {

    @Autowired
    private TbAlbumServiceImpl albumService;

    /***
     * Album分页条件搜索实现
     * @param album
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result< Page<TbAlbum> > findPage(@RequestBody(required = false) TbAlbum album, @PathVariable int page, @PathVariable int size) {
        //调用AlbumService实现分页条件查询Album
        QueryWrapper< TbAlbum > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(album.getId() != null, TbAlbum::getId, album.getId())
                .like(!StringUtils.isNullOrEmpty(album.getTitle()), TbAlbum::getTitle, album.getTitle());
        Page< TbAlbum > pagez = new Page<>(page, size);
        Page< TbAlbum > pageInfo = albumService.page(pagez,queryWrapper );
        return new Result(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * Album分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result< Page > findPage(@PathVariable int page, @PathVariable int size) {
        //调用AlbumService实现分页查询Album
        Page< TbAlbum > pagez = new Page<>(page, size);
        Page< TbAlbum > pageInfo = albumService.page(pagez);
        return new Result< Page >(true, StatusCode.OK, "查询成功", pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param album
     * @return
     */
    @PostMapping(value = "/search")
    public Result< List< TbAlbum > > findList(@RequestBody(required = false) TbAlbum album) {
        //调用AlbumService实现条件查询Album
        QueryWrapper< TbAlbum > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(album.getId() != null, TbAlbum::getId, album.getId())
                .like(!StringUtils.isNullOrEmpty(album.getTitle()), TbAlbum::getTitle, album.getTitle());
        List< TbAlbum > list = albumService.list(queryWrapper);
        return new Result< List< TbAlbum > >(true, StatusCode.OK, "查询成功", list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        //调用AlbumService实现根据主键删除
        albumService.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 修改Album数据
     * @param album
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody TbAlbum album, @PathVariable Long id) {
        //设置主键值
        album.setId(id);
        //调用AlbumService实现修改Album
        albumService.updateById(album);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /***
     * 新增Album数据
     * @param album
     * @return
     */
    @PostMapping
    public Result add(@RequestBody TbAlbum album) {
        //调用AlbumService实现添加Album
        albumService.save(album);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /***
     * 根据ID查询Album数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result< TbAlbum > findById(@PathVariable Long id) {
        //调用AlbumService实现根据主键查询Album
        TbAlbum album = albumService.getById(id);
        return new Result< TbAlbum >(true, StatusCode.OK, "查询成功", album);
    }

    /***
     * 查询Album全部数据
     * @return
     */
    @GetMapping
    public Result< List< TbAlbum > > findAll() {
        //调用AlbumService实现查询所有Album
        List< TbAlbum > list = albumService.list();
        return new Result< List< TbAlbum > >(true, StatusCode.OK, "查询成功", list);
    }
}
