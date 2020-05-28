package com.example.demo.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (TCorpus)实体类
 *  文集表
 * @author makejava
 * @since 2020-05-14 17:00:34
 */
@Data
public class TCorpus implements Serializable {
    private static final long serialVersionUID = 124063856764822479L;
    /**
    * 文集Id
    */
    private Long id;
    /**
    * 用户Id
    */
    private Long userId;
    /**
    * 文集名称
    */
    private String corpusName;
    /**
    * 文集创建时间
    */
    private String corpusCreateTime;
    /**
     * 文集状态 发布：1 未发布：0
     */
    private Integer status;

    private TUser user;



}