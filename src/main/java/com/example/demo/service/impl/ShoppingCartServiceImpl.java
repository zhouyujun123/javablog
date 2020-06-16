package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
    public ApiResult addOrUpdateCart(String userId, String productId) {
        boolean exists = redisService.existsValue(CartPrefix.getCartList, userId, productId);
        if (exists) {
            //获取现有的购物车中的数据
            String json = redisService.hget(CartPrefix.getCartList, userId, productId);
            CartDTO cart = JSON.parseObject(json, CartDTO.class);
            cart.setProductNum(cart.getProductNum() + 1);
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
            cartDto.setProductNum(1);
            redisService.hset(CartPrefix.getCartList, userId, productId, JSON.toJSONString(cartDto));
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
    }

    @Override
    public ApiResult delCartProduct(String userId, String productId) {
        Long hdel = redisService.hdel(CartPrefix.getCartList, userId, productId);
        if (hdel == 1L) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }
        return ApiResult.resultWith(ResultCodeEnum.ERROR);
    }

    @Override
    public ApiResult showCart(String userId) {
        List<String> jsonList = redisService.hvals(CartPrefix.getCartList, userId);
        List<CartDTO> cartDTOList = JSONArray.parseArray(JSON.toJSONString(jsonList), CartDTO.class);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, cartDTOList);
    }

    @Override
    public ApiResult delCart(String userId) {
        boolean delete = redisService.delete(CartPrefix.getCartList, userId);
        if (delete) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }
        return ApiResult.errorWith(ResultCodeEnum.ERROR);
    }

    @Override
    public ApiResult updateCartNum(String userId, String productId, int num) {
        String json = redisService.hget(CartPrefix.getCartList, userId, productId);
        if (json == null) {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        } else {
            CartDTO cart = JSON.parseObject(json, CartDTO.class);
            cart.setProductNum(num);
            redisService.hset(CartPrefix.getCartList, userId, productId, JSON.toJSONString(cart));
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }
    }
}
