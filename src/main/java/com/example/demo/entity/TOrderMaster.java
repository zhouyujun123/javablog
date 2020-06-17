package com.example.demo.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 联合订单(TOrderMaster)实体类
 *
 * @author makejava
 * @since 2020-06-17 09:49:48
 */
@Data
public class TOrderMaster implements Serializable {
    private static final long serialVersionUID = 779920190673267224L;
    
    private String orderId;
    /**
    * 买家名字
    */
    private String buyerName;
    /**
    * 买家电话
    */
    private String buyerPhone;
    /**
    * 买家地址
    */
    private String buyerAddress;
    /**
    * 买家id
    */
    private String buyerUserId;
    /**
    * 订单总金额
    */
    private Double orderAmount;
    /**
    * 订单状态, 默认为新下单
    */
    private Integer orderStatus;
    /**
    * 支付状态, 默认未支付
    */
    private Integer payStatus;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 修改时间
    */
    private String updateTime;


}