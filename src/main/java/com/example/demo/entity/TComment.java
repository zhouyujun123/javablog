package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TComment)实体类
 * 评论表
 * @author makejava
 * @since 2020-05-14 16:50:54
 */
@Data
public class TComment implements Serializable {
    private static final long serialVersionUID = 624675007850371384L;
    
    private Long id;
    /**
    * 文章Id
    */
    private Long articleId;
    /**
    * 文章评论
    */
    private String articleComment;




}