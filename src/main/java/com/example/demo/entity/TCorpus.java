package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TCorpus)实体类
 *
 * @author makejava
 * @since 2020-05-06 21:42:28
 */
public class TCorpus implements Serializable {
    private static final long serialVersionUID = -64265522764846700L;
    
    private Integer id;
    
    private String corpusId;
    
    private String userId;
    
    private String corpusName;
    
    private Date corpusTime;
    
    private Integer corpusNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorpusId() {
        return corpusId;
    }

    public void setCorpusId(String corpusId) {
        this.corpusId = corpusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCorpusName() {
        return corpusName;
    }

    public void setCorpusName(String corpusName) {
        this.corpusName = corpusName;
    }

    public Date getCorpusTime() {
        return corpusTime;
    }

    public void setCorpusTime(Date corpusTime) {
        this.corpusTime = corpusTime;
    }

    public Integer getCorpusNum() {
        return corpusNum;
    }

    public void setCorpusNum(Integer corpusNum) {
        this.corpusNum = corpusNum;
    }

}