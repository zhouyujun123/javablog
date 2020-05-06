package com.example.demo.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;

/**
 * (TArticle)实体类
 *
 * @author makejava
 * @since 2020-05-06 21:29:31
 */
public class TArticle implements Serializable {
    private static final long serialVersionUID = -32995513784742243L;
    
    private Integer id;
    
    private String articleId;
    
    private String articleName;
    
    private String articleAuthor;
    
    private String articleIntroduct;
    
    private String articleTime;
    
    private String articleContent;
    
    private String articleCorpusId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleIntroduct() {
        return articleIntroduct;
    }

    public void setArticleIntroduct(String articleIntroduct) {
        this.articleIntroduct = articleIntroduct;
    }

    public String getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(String articleTime) {
        this.articleTime = articleTime;
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

}