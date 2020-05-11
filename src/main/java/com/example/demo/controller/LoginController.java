package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.service.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:21
 */

@RestController
public class LoginController {

    @Autowired
    private LoginServer loginServer;

    /**
     * 登陆
     */
    @RequestMapping("/login")
    public ApiResult login(String username, String password){
        return loginServer.hasPeople(username, password);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/registered")
    public ApiResult registered(String username, String password){
        return loginServer.registered(username,password);
    }


}
