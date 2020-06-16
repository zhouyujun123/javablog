package com.example.demo.base;

/**
 * @Author mintaoyu
 * Date on 2020-06-16  14:42
 */
public class CartPrefix extends BasePrefix {

    public CartPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 设置购物车缓存
     */
    public static CartPrefix getCartList= new CartPrefix(0,"cart");
}
