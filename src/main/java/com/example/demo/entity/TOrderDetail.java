package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 店铺订单(TOrderDetail)实体类
 *
 * @author makejava
 * @since 2020-06-17 09:50:21
 */
@Data
public class TOrderDetail implements Serializable {
    private static final long serialVersionUID = -69997059395196000L;
    
    private String detailId;
    
    private String orderId;

    private String productId;
    /**
    * 商品名称
    */
    private String productName;
    /**
    * 当前价格,单位分
    */
    private Double productPrice;
    /**
    * 数量
    */
    private Integer productQuantity;
    /**
    * 小图
    */
    private String productIcon;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 修改时间
    */
    private String updateTime;



}