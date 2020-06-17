package com.example.demo.dao;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.TOrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 主订单(TOrderMaster)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-17 13:53:26
 */
@Mapper
public interface TOrderMasterDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    TOrderMaster queryById(String orderId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrderMaster> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tOrderMaster 实例对象
     * @return 对象列表
     */
    List<TOrderMaster> queryAll(TOrderMaster tOrderMaster);

    /**
     * 新增数据
     *
     * @param tOrderMaster 实例对象
     * @return 影响行数
     */
    int insert(OrderDTO tOrderMaster);

    /**
     * 修改数据
     *
     * @param tOrderMaster 实例对象
     * @return 影响行数
     */
    int update(TOrderMaster tOrderMaster);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

}