package com.example.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/31 16:12
 * Description:
 */
public enum OAuthWebSites {
    GitHub("https://github.com/login/oauth/access_token",
            "00cf4d5797929f0556d2",
            "aaa93eb63de719f44358483733912895e1eb2416",
            "https://github.com/login/oauth/authorize",
            "https://api.github.com/user",
            "/login/github");
    final String tokenUrl;
    final String clientId;
    final String clientSecret;
    final String authorizationBaseUrl;
    final String userInfo;
    final String logInUrl;

    OAuthWebSites(String tokenUrl, String clientId, String clientSecret, String authorizationBaseUrl, String userInfo, String logInUrl) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationBaseUrl = authorizationBaseUrl;
        this.userInfo = userInfo;
        this.logInUrl = logInUrl;
    }


    public static List<LoginPage> allPages(){
        List<LoginPage> pages=new ArrayList<>();
        for (OAuthWebSites oAuthWebSites : OAuthWebSites.values()) {
            LoginPage page=new LoginPage();
            page.setName(oAuthWebSites.name());
            page.setLoginPath(oAuthWebSites.logInUrl);
            pages.add(page);
        }
        return pages;
    }
}
