package com.example.demo.es.Model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * Created by baishuai on 2018/12/18
 */
@Data
public class Order {

    private Integer id;

    @JSONField(name="article_id")
    private int articleId;

    @JSONField(name="article_name")
    private String articleName;

    @JSONField(name="article_author")
    private String articleAuthor;

    @JSONField(name="article_introduct")
    private String articleIntroduct;

    @JSONField(name="article_time")
    private String articleTime;

    @JSONField(name="article_content")
    private String articleContent;

    @JSONField(name="article_corpusId")
    private String articleCorpusId;

    @JSONField(name="article_state")
    private Integer articleState;

}
