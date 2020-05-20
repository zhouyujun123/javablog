package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TCorpus;
import com.example.demo.entity.TSubscription;
import com.example.demo.entity.TUser;
import com.example.demo.service.TArticleService;
import com.example.demo.service.TCorpusService;
import com.example.demo.service.TSubscriptionService;
import com.example.demo.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private TUserService userService;

    @Autowired
    private TArticleService articleService;

    @Autowired
    private TCorpusService corpusService;

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
     * userId
     * type 0:用户 1:文章 2:文集
     */
    @RequestMapping("/findSub")
    public ApiResult findSub(TSubscription subscription) {
        Integer type = subscription.getType();
        List<TSubscription> list = tSubscriptionService.queryAll(subscription);
        List<TUser> userList = new ArrayList<>();
        List<TArticle> articleList = new ArrayList<>();
        List<TCorpus> corpusList = new ArrayList<>();
        if (type == 0) {
            for (TSubscription sub : list) {
                TUser user = userService.queryById(sub.getSubscriptionId());
                userList.add(user);
            }
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, userList);
        } else if (type == 1) {
            for (TSubscription sub : list) {
                TArticle article = articleService.queryById(sub.getSubscriptionId());
                articleList.add(article);
            }
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, articleList);
        } else {
            for (TSubscription sub : list) {
                TCorpus tCorpus = corpusService.queryById(sub.getSubscriptionId());
                corpusList.add(tCorpus);
            }
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, corpusList);
        }
    }
}