package com.me.oauth.service.impl;

import com.me.user.pojo.OauthClientDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author guokui
 * @class security
 * @date 2021/9/2 17:06
 *
 * 用于查询不同客户端的clientId和秘钥
 */
@Service("MyClientDetialService")
@Slf4j
public class MyClientDetialService implements ClientDetailsService {

    @Autowired
    private UserClient loginClient;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails =loginClient.findByOauthClientId(clientId).getData();
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        BeanUtils.copyProperties(oauthClientDetails,baseClientDetails);
        baseClientDetails.setAccessTokenValiditySeconds(oauthClientDetails.getAccessTokenValidity());
     //   baseClientDetails.setAdditionalInformation(oauthClientDetails.getAdditionalInformation());
     //   baseClientDetails.setAuthorities(oauthClientDetails.getAuthorities());
        if (StringUtils.isNotBlank(oauthClientDetails.getScope())) {
            Set scope = new HashSet< String >();
            scope = Arrays.stream(oauthClientDetails.getScope().split(",")).collect(Collectors.toSet());
            baseClientDetails.setScope(scope);
        }
        if (StringUtils.isNotBlank(oauthClientDetails.getAutoapprove())) {
            Set autoApproveScopes = Arrays.stream(oauthClientDetails.getAutoapprove().split(",")).collect(Collectors.toSet());
            baseClientDetails.setAutoApproveScopes(autoApproveScopes);
        }
        if (StringUtils.isNotBlank(oauthClientDetails.getAuthorizedGrantTypes())) {
            Set authorizedGrantTypes = Arrays.stream(oauthClientDetails.getAuthorizedGrantTypes().split(",")).collect(Collectors.toSet());
            baseClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
        }
        if (StringUtils.isNotBlank(oauthClientDetails.getResourceIds())) {
            Set resourceIds = Arrays.stream(oauthClientDetails.getResourceIds().split(",")).collect(Collectors.toSet());
            baseClientDetails.setResourceIds(resourceIds);
        }
        if (StringUtils.isNotBlank(oauthClientDetails.getWebServerRedirectUri())) {
            Set registeredRedirectUris = Arrays.stream(oauthClientDetails.getWebServerRedirectUri().split(",")).collect(Collectors.toSet());
            baseClientDetails.setRegisteredRedirectUri(registeredRedirectUris);
        }

        baseClientDetails.setRefreshTokenValiditySeconds(oauthClientDetails.getRefreshTokenValidity());
        return baseClientDetails;
    }
}
