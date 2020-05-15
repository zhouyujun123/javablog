package com.example.demo.entity;

import java.io.Serializable;

/**
 * (TImg)实体类
 * 图片表
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
public class TImg implements Serializable {
    private static final long serialVersionUID = -76891754898314951L;
    
    private Integer id;
    
    private Integer userId;
    /**
    * 文章url
    */
    private String imgUrl;


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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}