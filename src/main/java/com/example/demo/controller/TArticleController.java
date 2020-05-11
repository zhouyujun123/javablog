package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TCorpus;
import com.example.demo.service.TArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TArticle)表控制层
 *
 * @author makejava
 * @since 2020-05-06 21:29:49
 */
@RestController
@RequestMapping("tArticle")
public class TArticleController {
    /**
     * 服务对象
     */
    @Resource
    private TArticleService tArticleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TArticle selectOne(Integer id) {
        return this.tArticleService.queryById(id);
    }

    @RequestMapping("/findAllArticle")
    public ApiResult findAllArticle(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size, TArticle article) {
        PageHelper.startPage(page, size);
        List<TArticle> tArticles = tArticleService.queryAll(article);
        PageInfo<TArticle> pageInfo = new PageInfo<>(tArticles);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
    }

    /**
     *
     * @param id 文章id
     * @return
     */
    @GetMapping("/deleteById/{id}")
    public ApiResult deleteById(@PathVariable("id") Integer id){
        boolean delete = tArticleService.deleteById(id);
        if (delete){
           return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        }else {
            return ApiResult.errorWith(ResultCodeEnum.OPERATION_FAILED);
        }
    }

}