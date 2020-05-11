package com.example.demo.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;

/**
 * (TArticle)实体类
 *
 * @author makejava
 * @since 2020-05-06 21:29:31
 */
@Data
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

    private Integer articleState;
}