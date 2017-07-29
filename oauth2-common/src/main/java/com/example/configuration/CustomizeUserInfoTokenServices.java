package com.example.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/29 17:00
 * Description:
 */
public class CustomizeUserInfoTokenServices extends UserInfoTokenServices {
    String name;
    public CustomizeUserInfoTokenServices(String name,String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
        this.name=name;
        setPrincipalExtractor(new CustomizePrincipalExtractor());
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        OAuth2Authentication authentication= super.loadAuthentication(accessToken);
        Object principal= authentication.getPrincipal();
        if(principal instanceof Map){
            ((Map)principal).put("OAuthSource",name);
        }
        System.out.println(principal.getClass());
        return authentication;
    }
}
