package com.example.demo.server;

import com.example.demo.vo.ArticleVO;

import java.util.List;

public interface TestServer {
    /**
     *  删除数据到数据库
     */
    void delete(String id);

    /**
     * 插入数据到数据库
     */
    void addline(String id,String name,String time,String number);

    /**
     * @return ArticleVO
     */
    List<ArticleVO> searchAll(String userId);
}
