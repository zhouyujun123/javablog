package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.entity.TArticle;
import com.example.demo.service.TArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private ElasticsearchTemplate elasticSearchTemplate;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TArticle selectOne(Long id) {
        return this.tArticleService.queryById(id);
    }

    /**
     * 分页 按照条件查询
     *
     * @param page
     * @param size
     * @param article
     * @return
     */
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
            elasticSearchTemplate.delete(TArticle.class, id.toString());
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
            IndexQuery indexQuery = new IndexQueryBuilder().withObject(tArticle).build();
            elasticSearchTemplate.index(indexQuery);
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
            UpdateRequest request = new UpdateRequest("buy","_doc",tArticle.getId().toString());
            Map<String, Object> map = BeanUtil.beanToMap(tArticle,false,true);
            request.doc(toArgs(map));
            UpdateQuery updateQuery = new UpdateQueryBuilder().withId(tArticle.getId().toString()).withClass(TArticle.class).withUpdateRequest(request).build();
            elasticSearchTemplate.update(updateQuery);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }

    /**
     * 将批量数据转换成es参数数组
     * @param data
     * @return
     */
    private Object[] toArgs(Map<String, Object> data) {
        List<Object> args = new ArrayList<>();
        if (CollectionUtil.isEmpty(data)) {
            throw new RuntimeException("批量操作数据不可为空 !");
        }
        data.forEach((key, value) -> {
            args.add(key);
            args.add(value);
        });
        return args.toArray();
    }


}