package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TCorpus)实体类
 *  文集表
 * @author makejava
 * @since 2020-05-14 17:00:34
 */
public class TCorpus implements Serializable {
    private static final long serialVersionUID = 124063856764822479L;
    /**
    * 文集Id
    */
    private Integer id;
    /**
    * 用户Id
    */
    private Integer userId;
    /**
    * 文集名称
    */
    private String corpusName;
    /**
    * 文集创建时间
    */
    private Date corpusCreateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCorpusName() {
        return corpusName;
    }

    public void setCorpusName(String corpusName) {
        this.corpusName = corpusName;
    }

    public Date getCorpusCreateTime() {
        return corpusCreateTime;
    }

    public void setCorpusCreateTime(Date corpusCreateTime) {
        this.corpusCreateTime = corpusCreateTime;
    }

}