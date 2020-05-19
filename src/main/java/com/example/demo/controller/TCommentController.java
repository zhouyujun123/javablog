package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TComment;
import com.example.demo.service.TCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TComment)表控制层
 *
 * @author makejava
 * @since 2020-05-14 16:50:54
 * 文章评论
 */
@RestController
@RequestMapping("tComment")
public class TCommentController {

    @Autowired
    private TCommentService service;
    /**
     * 服务对象
     */
    @Resource
    private TCommentService tCommentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TComment selectOne(Integer id) {
        return this.tCommentService.queryById(id);
    }

    @RequestMapping("/addComment")
    public ApiResult addComment(TComment comment) {
        if (service.insert(comment) > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

    /**
     * 通过文章id找文章评论
     * @param articleId
     * @return
     */
    @GetMapping("/findComment/{articleId}")
    public ApiResult findComment(@PathVariable("articleId") Integer articleId) {
        List<TComment> tComments = service.queryAllByArticleId(articleId);
        if (tComments.size() > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, tComments);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

}