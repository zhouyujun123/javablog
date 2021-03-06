package com.example.demo.service;

import com.example.demo.base.ApiResult;
import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TUser;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * (TUser)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 17:08:18
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Long id);

    String queryNameById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(int offset, int limit);

    /**
     * 模糊查找
     * @param
     * @return
     */
    List<TUser> findLike(FindDTO find);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    ApiResult registered(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 从数据库中取出登陆信息
     */
    ApiResult login(String username, String password, HttpServletResponse response);


    /**
     * 该用户id 是否是该密码
     * @param userId
     * @param psw
     * @return
     */
    boolean hasPeopleByUserIdAndPsw(String userId,String psw);



}