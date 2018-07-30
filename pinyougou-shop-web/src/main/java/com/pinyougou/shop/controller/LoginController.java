package com.pinyougou.shop.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取登录用户名控制器
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-07-28<p>
 */
@RestController
public class LoginController {

    /** 获取登录用户名 */
    @GetMapping("/login/username")
    public Map<String, Object> loginName(){

        /** 获取SecurityContext上下对象 */
        SecurityContext securityContext = SecurityContextHolder.getContext();
        /** 获取登录用户名 */
        String loginName = securityContext.getAuthentication().getName();
        Map<String, Object> data = new HashMap<>();
        data.put("loginName", loginName);
        return data;
    }
}
