package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @param num       商品数量
     * @return
     */
    @GetMapping("/addCart")
    public ApiResult addCart(String userId, String productId, int num) {
        return shoppingCartService.addOrUpdateCart(userId, productId, num);
    }
}
