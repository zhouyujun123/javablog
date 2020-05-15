package com.example.demo.entity;

import java.io.Serializable;

/**
 * (TComment)实体类
 * 评论表
 * @author makejava
 * @since 2020-05-14 16:50:54
 */
public class TComment implements Serializable {
    private static final long serialVersionUID = 624675007850371384L;
    
    private Integer id;
    /**
    * 文章Id
    */
    private Integer articleId;
    /**
    * 文章评论
    */
    private String articleComment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(String articleComment) {
        this.articleComment = articleComment;
    }

}