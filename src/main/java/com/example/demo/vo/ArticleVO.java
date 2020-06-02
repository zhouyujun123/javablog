package com.example.demo.vo;

import com.example.demo.entity.TArticle;
import lombok.Data;

/**
 * @Author mintaoyu
 * Date on 2020-06-02  10:31
 */
@Data
public class ArticleVO extends TArticle {
    private Boolean hasSubscribed;
}
