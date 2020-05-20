package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TSubscription)实体类
 * 订阅表
 * @author makejava
 * @since 2020-05-14 16:50:32
 */
@Data
public class TSubscription implements Serializable {
    private static final long serialVersionUID = 294712587806004896L;
    
    private Long id;
    
    private Long userId;
    /**
    * 收藏类型  0:用户 1:文章 2:文集
    */
    private Integer type;
    /**
    * 订阅id 可以是userId、articleId、corpus_id  结合type字段形成唯一键
    */
    private Long subscriptionId;



}