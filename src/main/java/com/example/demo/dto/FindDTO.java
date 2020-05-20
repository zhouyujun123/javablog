package com.example.demo.dto;

import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-05-20  09:50
 */
@Data
public class FindDTO {
    /**
     * 收藏类型  0:用户 1:文章 2:文集
     */
    private Integer type;
    /**
     * 搜索正文
     */
    private String text;
    /**
     *  用户ID
     */
    private String userId;
}
