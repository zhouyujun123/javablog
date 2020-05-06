package com.example.demo.controller;

import com.example.demo.po.ArticlePO;
import com.example.demo.server.TestServer;
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

    /**
     * 删除数据
     * @param id 所删除数据的id
     * @return
     */
    @RequestMapping("/test")
    public String test(String id) {
        server.delete(id);
        return "删除成功";
    }

    /**
     * 添加成功
     * @param id 添加数据的id
     * @param name 添加的数据的名字
     * @param time 添加时间
     * @param number 文集中的文章数
     * @return
     */
    @RequestMapping("/add")
    public String add(String id,String name,String time,String number) {
        server.addline(id,name,time,number);
//        return id + name + time + number;
        return "添加成功";
    }

    /**
     *
     * @param userId 根据用户id搜索数据
     * @return
     */
    @RequestMapping("/listAllArticle/{userId}")
    public List<ArticlePO> listAllArticle(@PathVariable("userId") String userId){
        return server.searchAll(userId);
    }

    /**
     *
     * @param userId 根据用户id搜索数据
     * @return
     */
//    @RequestMapping("/listAllArticle/{userId}")
//    public List<CorpusVO> listAllArticle(@PathVariable("userId") String userId){
//        return server.searchAll(userId);
//    }
}
