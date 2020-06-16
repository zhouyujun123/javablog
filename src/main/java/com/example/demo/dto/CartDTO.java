package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: le
 * @Date: 2018/9/5 15:58
 * @Description:
 */
@Data
public class CartDTO implements Serializable{

    private static final long serialVersionUID = -8222708126799272022L;

    private String productId;
    private BigDecimal productPrice;
    private Integer productNum;
    /*是否勾选*/
    private String check;
    private String productName;
    /**
     * 状态, 0正常1下架.
     */
    private Integer productStatus;

    /* 商品小图*/
    private String productIcon;
}
