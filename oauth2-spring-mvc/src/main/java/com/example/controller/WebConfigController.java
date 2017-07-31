package com.example.controller;

import com.example.configuration.LoginPage;
import com.example.configuration.OAuthWebSites;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/31 16:22
 * Description:
 */
@RestController
@RequestMapping("openData")
public class WebConfigController {
    @RequestMapping({"/loginPages"})
    public List<LoginPage> loginPages() {
        return OAuthWebSites.allPages();
    }
}
