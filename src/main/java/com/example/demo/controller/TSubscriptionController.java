package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TSubscription;
import com.example.demo.service.TSubscriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TSubscription)表控制层
 *
 * @author makejava
 * @since 2020-05-14 16:50:32
 */
@RestController
@RequestMapping("tSubscription")
public class TSubscriptionController {
    /**
     * 服务对象
     */
    @Resource
    private TSubscriptionService tSubscriptionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TSubscription selectOne(Integer id) {
        return this.tSubscriptionService.queryById(id);
    }

    /**
     * 用户订阅
     */
    @RequestMapping("/userSub")
    public ApiResult userSub(TSubscription subscription) {
        if (tSubscriptionService.insert(subscription) > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.resultWith(ResultCodeEnum.ERROR);
        }
    }
    /**
     * 查询订阅的信息
     */
//    @RequestMapping("/findSub")
//    public ApiResult findSub(TSubscription subscription){
//        Integer type = subscription.getType();
//
//    }
}