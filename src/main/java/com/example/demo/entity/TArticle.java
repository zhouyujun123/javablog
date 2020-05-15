package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TArticle)实体类
 * 文章表
 * @author makejava
 * @since 2020-05-14 17:00:46
 */
public class TArticle implements Serializable {
    private static final long serialVersionUID = -34674011594388686L;
    /**
    * 文章id
    */
    private Integer id;
    /**
    * 文章名称
    */
    private String articleName;
    /**
    * 文章作者
    */
    private Integer userId;
    /**
    * 文章介绍
    */
    private String articleIntroduct;
    /**
    * 文章创建时间
    */
    private Date articleCreateTime;
    /**
    * 文章正文
    */
    private String articleContent;
    /**
    * 文章所属文集Id
    */
    private String articleCorpusId;
    /**
    * 文章状态
    */
    private Integer articleState;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getArticleIntroduct() {
        return articleIntroduct;
    }

    public void setArticleIntroduct(String articleIntroduct) {
        this.articleIntroduct = articleIntroduct;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleCorpusId() {
        return articleCorpusId;
    }

    public void setArticleCorpusId(String articleCorpusId) {
        this.articleCorpusId = articleCorpusId;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

}