package com.me.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.me.user.pojo.TbAddress;
import com.me.user.service.impl.TbAddressServiceImpl;
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
 * @since 2021-12-24
 */
@Controller
@RequestMapping("/address")
public class TbAddressController {

    @Autowired
    private TbAddressServiceImpl addressService;
    /**
     * TbAddress构建查询对象
     * @param template
     * @return
     */
    private QueryWrapper< TbAddress > createExample(TbAddress template){
        QueryWrapper< TbAddress > queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(template.getId() != null, TbAddress::getId, template.getId())
                .eq(!StringUtils.isNullOrEmpty(template.getUsername()), TbAddress::getProvinceid, template.getProvinceid())
                .eq(!StringUtils.isNullOrEmpty(template.getCityid()), TbAddress::getCityid, template.getCityid())
                .eq(!StringUtils.isNullOrEmpty(template.getAreaid()), TbAddress::getAreaid, template.getAreaid())
                .eq(!StringUtils.isNullOrEmpty(template.getPhone()), TbAddress::getPhone, template.getPhone())
                .eq(!StringUtils.isNullOrEmpty(template.getAddress()), TbAddress::getAddress, template.getAddress())
                .eq(!StringUtils.isNullOrEmpty(template.getContact()), TbAddress::getContact, template.getContact())
                .eq(!StringUtils.isNullOrEmpty(template.getIsDefault()), TbAddress::getIsDefault, template.getIsDefault())
                .eq(!StringUtils.isNullOrEmpty(template.getAlias()), TbAddress::getAlias, template.getAlias())
                .like(!StringUtils.isNullOrEmpty(template.getUsername()), TbAddress::getUsername, template.getUsername())
        ;
        return queryWrapper;
    }
    /***
     * Address分页条件搜索实现
     * @param address
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result< Page< TbAddress > > findPage(@RequestBody(required = false)  TbAddress address, @PathVariable int page, @PathVariable  int size){
        //调用AddressService实现分页条件查询Address
        Page<TbAddress> pagez = new Page<>(page,size);
        QueryWrapper< TbAddress > queryWrapper =this.createExample(address);
        Page<TbAddress> pageInfo = addressService.page(pagez, queryWrapper);
        return new Result(true, StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Address分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<Page> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页查询Address
        Page<TbAddress> pagez = new Page<>(page,size);
        Page<TbAddress> pageInfo = addressService.page(pagez);
        return new Result<Page>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param address
     * @return
     */
    @PostMapping(value = "/search" )
    public Result< List<TbAddress> > findList(@RequestBody(required = false)  TbAddress address){
        //调用AddressService实现条件查询Address
        QueryWrapper< TbAddress > queryWrapper =this.createExample(address);

        List<TbAddress> list = addressService.list(queryWrapper);
        return new Result<List<TbAddress>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用AddressService实现根据主键删除
        addressService.removeById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Address数据
     * @param address
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  TbAddress address,@PathVariable Integer id){
        //设置主键值
        address.setId(id);
        //调用AddressService实现修改Address
        addressService.updateById(address);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Address数据
     * @param address
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   TbAddress address){
        //调用AddressService实现添加Address
        addressService.save(address);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Address数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<TbAddress> findById(@PathVariable Integer id){
        //调用AddressService实现根据主键查询Address
        TbAddress address = addressService.getById(id);
        return new Result<TbAddress>(true,StatusCode.OK,"查询成功",address);
    }

    /***
     * 查询Address全部数据
     * @return
     */
    @GetMapping
    public Result<List<TbAddress>> findAll(){
        //调用AddressService实现查询所有Address
        List<TbAddress> list = addressService.list();
        return new Result<List<TbAddress>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
