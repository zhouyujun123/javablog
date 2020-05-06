package com.example.demo.dao;

import com.example.demo.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:39
 */
@Mapper
public interface LoginDAO {

    /**
     * 是否有该用户
     * @param username
     * @param password
     * @return
     */
    UserPO hasPeople(@Param("username") String username, @Param("password") String password);

}
