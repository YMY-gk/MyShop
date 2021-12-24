package com.me.user.service.impl;

import com.me.user.pojo.OauthCode;
import com.me.user.mapper.OauthCodeMapper;
import com.me.user.service.IOauthCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author guokui
 * @since 2021-12-24
 */
@Service
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements IOauthCodeService {

}
