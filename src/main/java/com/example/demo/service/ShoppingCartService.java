package com.example.demo.service;

import com.example.demo.base.ApiResult;
import com.example.demo.dto.CartDTO;

import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-06-16  13:54
 * 购物车 服务类
 */
public interface ShoppingCartService {
    /**
     * 添加购物车
     * 判断redis是否有key，如果有则添加数量，如果没有则新增购物车商品
     */
    ApiResult addOrUpdateCart(String userId, String productId);

    /**
     * 删除购物车商品
     *
     * @param userId
     * @param productId
     * @return
     */
    ApiResult delCartProduct(String userId, String productId);

    /**
     * 展示购物车商品
     */
    ApiResult showCart(String userId);

    /**
     * 清空购物车
     */
    ApiResult delCart(String userId);


}
