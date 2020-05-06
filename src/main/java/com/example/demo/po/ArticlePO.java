package com.example.demo.po;

import lombok.Data;

/**
 * 返回给前端的数据
 * @data 自动生成get set方法
 */
@Data
public class ArticlePO {
    private String corpusId;
    private String corpusName;
    private String corpusTime;
    private String corpusNum;
}
