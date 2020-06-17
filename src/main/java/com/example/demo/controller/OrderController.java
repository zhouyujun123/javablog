package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.NormalConstant;
import com.example.demo.dto.OrderDTO;
import com.example.demo.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author mintaoyu
 * Date on 2020-06-17  09:11
 * 订单
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    /**
     * 提交订单
     *
     * @param orderDTO
     * @return
     */
    @PostMapping("/creatOrder")
    public ApiResult creatOrder(OrderDTO orderDTO) {
        orderService.creatOrder(orderDTO);
        return null;
    }

    @GetMapping("/delOrder/{orderId}")
    public ApiResult delOrder(@PathVariable("orderId") String orderId) {
        return orderService.delOrder(orderId);
    }


    /**
     * 订单详情列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/getOrderList")
    public ApiResult listOrder(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size, HttpServletRequest request) {
        String userId = (String) request.getAttribute(NormalConstant.USER_ID);
        PageHelper.startPage(page, size);
        return orderService.getOrderList(userId);
    }

    /**
     * 获取某一订单详情
     */
    @GetMapping("/getOrderDetail/{orderId}")
    public ApiResult getOrderDetail(@PathVariable("orderId") String orderId) {
        return null;
    }
}
