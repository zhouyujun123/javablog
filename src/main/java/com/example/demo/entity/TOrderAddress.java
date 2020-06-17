package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 收货地址表(TOrderAddress)实体类
 *
 * @author makejava
 * @since 2020-06-17 10:32:36
 */
@Data
public class TOrderAddress implements Serializable {
    private static final long serialVersionUID = 686225546319035623L;

    private Integer id;
    /**
     * 收货人名称
     */
    private String buyerName;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 收货地址
     */
    private String adress;
    /**
     * 号码
     */
    private String phone;

    private String createTime;

    private String updateTime;


}