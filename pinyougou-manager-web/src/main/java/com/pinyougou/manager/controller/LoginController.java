package com.pinyougou.manager.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: pinyougou
 * @description: 显示登录用户名控制器
 * @author: Mr.Chen
 * @create: 2018-07-29 21:57
 **/
@RestController
public class LoginController {
    /** 获取当前登录用户名 */
    @GetMapping("/login/username")
    public Map<String,String> loginName() {
        /** 获取SecurityContext对象 */
        SecurityContext securityContext = SecurityContextHolder.getContext();
        /** 获取当前登录用户名 */
        String name = securityContext.getAuthentication().getName();
        HashMap<String, String> data = new HashMap<>();
        data.put("loginName",name);
        return data;
    }
}
