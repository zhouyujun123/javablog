package com.example.demo.entity;

import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:37
 * 用户表
 */
@Data
public class TUser {
    private Integer id;
    private String userName;
    private String userPsw;
    private String userEmail;
    private Integer sex;
    private String nickName;
    private String birthYear;
    /**
     * 自我简介
     */
    private String introduction;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 权限Id
     */
    private Integer roleId;

    private TRole role;
}
