package com.me.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.me.user.pojo.UndoLog;
import com.me.user.service.impl.UndoLogServiceImpl;
import com.mysql.cj.util.StringUtils;
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
@RequestMapping("/undoLog")
@CrossOrigin
public class UndoLogController {

    @Autowired
    private UndoLogServiceImpl undoLogService;

    /***
     * UndoLog分页条件搜索实现
     * @param undoLog
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result< Page > findPage(@RequestBody(required = false)  UndoLog undoLog, @PathVariable int page, @PathVariable  int size){
        //调用UndoLogService实现分页条件查询UndoLog
        QueryWrapper< UndoLog > queryWrapper = createExample(undoLog);
        Page< UndoLog > pagez = new Page<>(page, size);
        Page<UndoLog> pageInfo = undoLogService.page(pagez,queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * UndoLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UndoLogService实现分页查询UndoLog

        Page< UndoLog > pagez = new Page<>(page, size);
        Page<UndoLog> pageInfo = undoLogService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param undoLog
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<UndoLog> > findList(@RequestBody(required = false)  UndoLog undoLog){
        //调用UndoLogService实现条件查询UndoLog
        QueryWrapper< UndoLog > queryWrapper = createExample(undoLog);
        List<UndoLog> list = undoLogService.list(queryWrapper);
        return new Result<List<UndoLog>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用UndoLogService实现根据主键删除
        undoLogService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改UndoLog数据
     * @param undoLog
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody UndoLog undoLog, @PathVariable Long id){
        //设置主键值
        undoLog.setId(id);
        //调用UndoLogService实现修改UndoLog
        undoLogService.updateById(undoLog);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增UndoLog数据
     * @param undoLog
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   UndoLog undoLog){
        //调用UndoLogService实现添加UndoLog
        undoLogService.save(undoLog);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询UndoLog数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<UndoLog> findById(@PathVariable Long id){
        //调用UndoLogService实现根据主键查询UndoLog
        UndoLog undoLog = undoLogService.getById(id);
        return new Result<UndoLog>(true,StatusCode.OK,"查询成功",undoLog);
    }

    /***
     * 查询UndoLog全部数据
     * @return
     */
    @GetMapping
    public Result<List<UndoLog>> findAll(){
        //调用UndoLogService实现查询所有UndoLog
        List<UndoLog> list = undoLogService.list();
        return new Result<List<UndoLog>>(true, StatusCode.OK,"查询成功",list) ;
    }
    /**
     * Album构建查询对象
     * @param undoLog
     * @return
     */
    private   QueryWrapper< UndoLog > createExample(UndoLog undoLog){
        QueryWrapper< UndoLog > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(undoLog.getId() != null, UndoLog::getId, undoLog.getId())
                .eq(undoLog.getLogModified() != null, UndoLog::getLogModified, undoLog.getLogModified())
                .eq(undoLog.getLogCreated() != null, UndoLog::getLogCreated, undoLog.getLogCreated())
                .eq(undoLog.getLogStatus() != null, UndoLog::getLogStatus, undoLog.getLogStatus())
                .eq(undoLog.getRollbackInfo() != null, UndoLog::getRollbackInfo, undoLog.getRollbackInfo())
                .eq(undoLog.getBranchId() != null, UndoLog::getBranchId, undoLog.getBranchId())
                .eq(!StringUtils.isNullOrEmpty(undoLog.getXid()), UndoLog::getXid, undoLog.getXid())
                .eq(!StringUtils.isNullOrEmpty(undoLog.getExt()), UndoLog::getExt, undoLog.getExt())
        ;
        return queryWrapper;
    }
}
