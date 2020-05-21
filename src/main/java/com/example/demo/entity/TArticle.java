package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * (TArticle)实体类
 * 文章表
 * @author makejava
 * @since 2020-05-14 17:00:46
 */
@Data
public class TArticle implements Serializable {
    private static final long serialVersionUID = -34674011594388686L;
    /**
    * 文章id
    */
    @Id
    private Long id;
    /**
    * 文章名称
    */
    private String articleName;
    /**
    * 文章作者Id
    */
    private Long userId;
    /**
    * 文章介绍
    */
    private String articleIntroduct;
    /**
    * 文章创建时间
    */
    private String articleCreateTime;
    /**
    * 文章正文
    */
    private String articleContent;
    /**
    * 文章所属文集Id
    */
    private Long articleCorpusId;
    /**
    * 文章状态
    */
    private Integer articleState;




}