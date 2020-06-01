package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TComment)实体类
 * 评论表
 *
 * @author makejava
 * @since 2020-05-14 16:50:54
 */
@Data
public class TComment implements Serializable {
    private static final long serialVersionUID = 624675007850371384L;
    /**
     * 评论Id
     */
    private Long id;
    /**
     * 文章Id
     */
    private Long articleId;
    /**
     * 文章评论
     */
    private String articleComment;
    /**
     * 评论用户id
     */
    private Long fromUserId;
    /**
     * 评论目标用户id
     */
    private Long toUserId;
    /**
     * 评论上级Id 上级Id为0说明没有上级评论
     */
    private Long previousId;
    /**
     * 评论日期
     */
    private String commentTime;



}