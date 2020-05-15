package com.example.demo.controller;

import com.example.demo.entity.TSubscription;
import com.example.demo.service.TSubscriptionService;
import org.springframework.web.bind.annotation.*;

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

}