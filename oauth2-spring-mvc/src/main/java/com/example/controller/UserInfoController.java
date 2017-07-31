package com.example.controller;

import com.example.configuration.SpringUserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/29 15:55
 * Description:
 */

@RestController
@RequestMapping("user")
public class UserInfoController {
    @RequestMapping({"/current", "/me"})
    public Object user() {
        return SpringUserUtil.getCurrentUser();
    }
}
