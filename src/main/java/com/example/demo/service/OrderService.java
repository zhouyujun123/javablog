package com.example.demo.service;

import com.example.demo.base.ApiResult;
import com.example.demo.dto.OrderDTO;

/**
 * @Author mintaoyu
 * Date on 2020-06-17  09:32
 */
public interface OrderService {
    ApiResult creatOrder(OrderDTO orderDTO);

    ApiResult delOrder(String orderId);

    ApiResult getOrderList(String userId);


    ApiResult getOrderDetail(String orderId);

}
