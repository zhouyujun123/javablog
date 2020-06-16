package com.example.demo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: le
 * @Date: 2018/7/26 16:39
 * @Description:
 */
// 商品详情
@Data
public class ProductInfo {

    private String productId;

    /**
     * 名字.
     */
    private String productName;

    /**
     * 单价.
     */
    private BigDecimal productPrice;

    /**
     * 库存.
     */
    private Integer productStock;

    /**
     * 描述.
     */
    private String productDescription;

    /**
     * 小图.
     */
    private String productIcon;

    /**
     * 状态, 0正常1下架.
     */
    private Integer productStatus;

    /**
     * 状态, 0正常状态 1轮播图 2热销商品 3新品.
     */
    private Integer productShow;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}
