package com.example.demo.server.impl;

import com.example.demo.dao.TestDAO;
import com.example.demo.po.ArticlePO;
import com.example.demo.server.TestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServerImpl implements TestServer {

    @Autowired
    private TestDAO testDao;

    @Override
    public void delete(String id){
        // 如何将数据删除数据库
        testDao.deleteById(id);
    }

    @Override
    public void addline(String id,String name,String time,String number){
        // 如何将数据插入数据库
        testDao.addById(id,name,time,number);
    }

    @Override
    public List<ArticlePO> searchAll(String userId){
        List<ArticlePO> search = testDao.searchAll(userId);
        return search;
    }
}
