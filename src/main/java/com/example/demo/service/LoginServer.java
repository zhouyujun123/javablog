package com.example.demo.service;

import com.example.demo.base.ApiResult;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:31
 */
public interface LoginServer {

    /**
     * 从数据库中取出登陆信息
     */
    ApiResult login(String username, String password, HttpServletResponse response);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    ApiResult registered(String username, String password,String mailbox);

}
