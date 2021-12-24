package com.me.user.service.impl;

import com.me.user.pojo.OauthRefreshToken;
import com.me.user.mapper.OauthRefreshTokenMapper;
import com.me.user.service.IOauthRefreshTokenService;
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
public class OauthRefreshTokenServiceImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements IOauthRefreshTokenService {

}
