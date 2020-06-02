package com.example.demo.controller;

import com.example.demo.base.ApiResult;
import com.example.demo.base.NormalConstant;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TArticle;
import com.example.demo.service.TArticleService;
import com.example.demo.service.TSubscriptionService;
import com.example.demo.vo.ArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private TSubscriptionService subService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ApiResult selectOne(HttpServletRequest request, Long id) {
        TArticle article = this.tArticleService.queryById(id);
        String myUserId = (String) request.getAttribute(NormalConstant.USER_ID);
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        boolean sub = subService.isSub(myUserId, article.getId().toString(), NormalConstant.ARTICLE_TYPE);
        if (sub) {
            articleVO.setHasSubscribed(true);
        } else {
            articleVO.setHasSubscribed(false);
        }
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, articleVO);
    }

    /**
     * 分页 按照条件查询
     *
     * @param page
     * @param size
     * @param article
     * @return
     */
//    @RoleCheck(roles = {"OWNER","USER"})
    @GetMapping("/findAllArticle")
    public ApiResult findAllArticle(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size, TArticle article) {
        PageHelper.startPage(page, size);
        List<TArticle> tArticles = tArticleService.queryAll(article);
        PageInfo<TArticle> pageInfo = new PageInfo<>(tArticles);
        return ApiResult.resultWith(ResultCodeEnum.SUCCESS, pageInfo);
    }

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return
     */
    @GetMapping("/deleteById/{id}")
    public ApiResult deleteById(@PathVariable("id") Integer id) {
        boolean delete = tArticleService.deleteById(id);
        if (delete) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.OPERATION_FAILED);
        }
    }

    /**
     * 添加文章
     */
    @PostMapping("/addArticle")
    public ApiResult addCorpus(TArticle tArticle) {
        if (tArticleService.insert(tArticle)) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

    /**
     * 修改文章
     * id 和 修改内容
     *
     * @param tArticle
     * @return
     */
    @PostMapping("/updateArticle")
    public ApiResult updateArticle(TArticle tArticle) {
        if (tArticleService.update(tArticle) > 0) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }


}