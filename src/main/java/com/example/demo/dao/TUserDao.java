package com.example.demo.dao;

import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-14 17:08:18
 */
@Mapper
public interface TUserDao {

    int hasPeopleRegistered(@Param("username") String username,@Param("mailbox")String mailbox);


    TUser hasPeople(@Param("username") String username,@Param("password")String password);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tUser 实例对象
     * @return 对象列表
     */
    List<TUser> queryAll(TUser tUser);


    List<TUser> findLike(FindDTO find);

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int insert(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 影响行数
     */
    int update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}