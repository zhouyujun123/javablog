package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.base.ApiResult;
import com.example.demo.base.CartPrefix;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dao.ProductInfoDao;
import com.example.demo.dto.CartDTO;
import com.example.demo.entity.ProductInfo;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-06-16  14:00
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private RedisService redisService;

    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public ApiResult addOrUpdateCart(String userId, String productId, int num) {
        boolean exists = redisService.existsValue(CartPrefix.getCartList, userId, productId);
        if (exists) {
            //获取现有的购物车中的数据
            String json = redisService.hget(CartPrefix.getCartList, userId, productId);
            CartDTO cart = JSON.parseObject(json, CartDTO.class);
            cart.setProductNum(num + cart.getProductNum());
            redisService.hset(CartPrefix.getCartList, userId, productId, JSON.toJSONString(cart));
        } else {
            // 根据商品Id查询商品
            ProductInfo productInfo = productInfoDao.findProductById(productId);
            CartDTO cartDto = new CartDTO();
            cartDto.setProductId(productId);
            cartDto.setProductName(productInfo.getProductName());
            cartDto.setProductIcon(productInfo.getProductIcon());
            cartDto.setProductPrice(productInfo.getProductPrice());
            cartDto.setProductStatus(productInfo.getProductStatus());
            cartDto.setProductNum(num);
            redisService.hset(CartPrefix.getCartList, userId, productId, JSON.toJSONString(cartDto));
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
    }

    @Override
    public int delCartProduct(String userId, String productId, int num) {
        return 0;
    }

    @Override
    public List<CartDTO> showCart(String userId) {
        return null;
    }

    @Override
    public int delCart() {
        return 0;
    }
}
