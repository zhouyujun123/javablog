package com.example.demo.controller;

import com.example.demo.server.TestServer;
import com.example.demo.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class testController {

    @Autowired
    private TestServer server;

    @RequestMapping("/test")
    public String test(String id) {
        server.delete(id);
        return "删除成功";
    }

    @RequestMapping("/add")
    public String add(String id,String name,String time,String number) {
        server.addline(id,name,time,number);
        return "添加成功";
    }

    @RequestMapping("/listAllArticle/{userId}")
    public List<ArticleVO> listAllArticle(@PathVariable("userId") String userId){
        return server.searchAll(userId);
    }
}
