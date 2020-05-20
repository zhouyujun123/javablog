package com.example.demo.es.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.TArticle;
import com.example.demo.service.TArticleService;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author mintaoyu
 * Date on 2020-05-13  16:21
 */
@RestController
@RequestMapping("/test")
public class ElasticSearchController {

    @Autowired
    private TArticleService articleService;

//    @Autowired
//    BulkProcessor bulkProcessor;

    @Autowired
    private ElasticsearchTemplate elasticSearchTemplate;

//    /**
//     * 同步文章到ElasticSearch
//     */
//    @RequestMapping("/SynchronizeES")
//    public void SynchronizeES() {
//        List<TArticle> tArticles = articleService.queryAll(null);
//        int i = 0;
//        for (TArticle article : tArticles) {
//            String jsonString = JSON.toJSONString(article);
//            bulkProcessor.add(new IndexRequest("article_index",
//                    "article_index", i + "")
//                    .source(jsonString, XContentType.JSON));
//            i++;
//        }
//    }
}
