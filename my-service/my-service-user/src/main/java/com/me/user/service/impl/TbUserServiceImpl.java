package com.me.user.service.impl;

import com.me.user.pojo.TbUser;
import com.me.user.mapper.TbUserMapper;
import com.me.user.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

}
