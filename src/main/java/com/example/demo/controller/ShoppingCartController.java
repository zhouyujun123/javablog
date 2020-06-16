package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author mintaoyu
 * Date on 2020-06-16  09:40
 * 购物车
 */
@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * @param userId    用户Id
     * @param productId 商品Id
     * @return
     */
    @GetMapping("/addCart")
    public ApiResult addCart(String userId, String productId) {
        return shoppingCartService.addOrUpdateCart(userId, productId);
    }

    /**
     * 清空购物车
     *
     * @param userId
     * @return
     */
    @GetMapping("/delCart")
    public ApiResult delCart(String userId) {
        return shoppingCartService.delCart(userId);
    }

    /**
     * 购物车展示列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/showCart")
    public ApiResult showCart(String userId) {
        return shoppingCartService.showCart(userId);
    }

    /**
     * 删除购物车商品
     */
    @GetMapping("/delCartProduct")
    public ApiResult delCartProduct(String userId, String productId) {
        return shoppingCartService.delCartProduct(userId, productId);
    }


}
