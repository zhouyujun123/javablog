package com.example.demo.vo;

import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  20:21
 */
@Data
public class LoginVO {
    private String userName;
    private String userId;

    public LoginVO() {
    }

    public LoginVO(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }
}
