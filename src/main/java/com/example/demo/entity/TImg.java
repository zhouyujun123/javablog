package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TImg)实体类
 * 图片表
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
@Data
public class TImg implements Serializable {
    private static final long serialVersionUID = -76891754898314951L;
    
    private Long id;
    
    private Long userId;
    /**
    * 文章url
    */
    private String imgUrl;



}