package com.example.demo.es.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.TArticle;
import com.example.demo.service.TArticleService;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @Author mintaoyu
 * Date on 2020-05-13  16:21
 */
@RestController
@RequestMapping("/test")
public class ElasticSearchController {

    @Autowired
    private TArticleService articleService;


    @Autowired
    private ElasticsearchTemplate elasticSearchTemplate;

    /**
     * 同步文章到ElasticSearch
     */
    @RequestMapping("/SynchronizeES")
    public List<TArticle> SynchronizeES() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .build();
        List<TArticle> tArticles = elasticSearchTemplate.queryForList(searchQuery, TArticle.class);
        tArticles.forEach(System.out::println);
        return tArticles;
    }
}
