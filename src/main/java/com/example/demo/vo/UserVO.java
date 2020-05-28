package com.example.demo.vo;

import com.example.demo.entity.TRole;
import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:37
 * 用户表
 */
@Data
public class UserVO {
    private Integer id;
    private String userEmail;
    private Integer sex;
    private String nickName;
    private String birthDate;
    /**
     * 自我简介
     */
    private String introduction;
    /**
     * 头像
     */
    private String headImg;
}
