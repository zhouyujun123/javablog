package com.example.demo.dao;

import com.example.demo.entity.TSubscription;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TSubscription)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-14 16:50:32
 */
@Mapper
public interface TSubscriptionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSubscription queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TSubscription> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tSubscription 实例对象
     * @return 对象列表
     */
    List<TSubscription> queryAll(TSubscription tSubscription);

    List<TSubscription> queryAllByUserId(String userId,String type);

    int isSub(String subscriberId,String subscribedId,String type);



    /**
     * 新增数据
     *
     * @param tSubscription 实例对象
     * @return 影响行数
     */
    int insert(TSubscription tSubscription);

    /**
     * 修改数据
     *
     * @param tSubscription 实例对象
     * @return 影响行数
     */
    int update(TSubscription tSubscription);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}