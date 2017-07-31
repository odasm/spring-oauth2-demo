package com.example.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/29 17:17
 * Description:
 */
public class SpringUserUtil {

    private static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null) {
            return null;
        }

        return context.getAuthentication();
    }

    public static Object getCurrentUser(){
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return principal;
    }
}
