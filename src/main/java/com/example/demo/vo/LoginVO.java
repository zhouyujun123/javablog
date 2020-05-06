package com.example.demo.vo;

import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  20:21
 */
@Data
public class LoginVO {
    private String token;
    private String userName;
    private String userId;

    public LoginVO() {
    }

    public LoginVO(String token, String userName, String userId) {
        this.token = token;
        this.userName = userName;
        this.userId = userId;
    }
}
