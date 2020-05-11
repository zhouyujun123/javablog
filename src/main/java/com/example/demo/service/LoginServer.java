package com.example.demo.service;

import com.example.demo.base.ApiResult;
import com.example.demo.po.UserPO;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:31
 */
public interface LoginServer {

    /**
     * 从数据库中取出登陆信息
     */
    ApiResult hasPeople(String username, String password);

}
