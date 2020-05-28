package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TCorpus;
import com.example.demo.entity.TUser;
import com.example.demo.service.TArticleService;
import com.example.demo.service.TCorpusService;
import com.example.demo.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-05-19  17:25
 * 模糊查找功能
 */
@RestController
public class FindLikeController {

    @Autowired
    private TUserService userService;

    @Autowired
    private TArticleService articleService;

    @Autowired
    private TCorpusService corpusService;

    @GetMapping("findLike")
    public ApiResult findLike(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size, FindDTO find) {
        PageHelper.startPage(page, size);
        Integer type = find.getType();
        if (type == 0) {
            List<TUser> userList = userService.findLike(find);
            PageInfo<TUser> pageInfo = new PageInfo<>(userList);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
        } else if (type == 1) {
            List<TArticle> articleList = articleService.findLike(find);
            PageInfo<TArticle> pageInfo = new PageInfo<>(articleList);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
        } else {
            List<TCorpus> corpusList = corpusService.findLike(find);
            PageInfo<TCorpus> pageInfo = new PageInfo<>(corpusList);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
        }
    }
}
