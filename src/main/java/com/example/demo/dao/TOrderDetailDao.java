package com.example.demo.dao;

import com.example.demo.entity.TOrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 子订单(TOrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-17 13:53:36
 */
@Mapper
public interface TOrderDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param detailId 主键
     * @return 实例对象
     */
    TOrderDetail queryById(String detailId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrderDetail> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tOrderDetail 实例对象
     * @return 对象列表
     */
    List<TOrderDetail> queryAll(TOrderDetail tOrderDetail);

    /**
     * 新增数据
     *
     * @param tOrderDetail 实例对象
     * @return 影响行数
     */
    int insert(TOrderDetail tOrderDetail);

    /**
     * 修改数据
     *
     * @param tOrderDetail 实例对象
     * @return 影响行数
     */
    int update(TOrderDetail tOrderDetail);

    /**
     * 通过主键删除数据
     *
     * @param detailId 主键
     * @return 影响行数
     */
    int deleteById(String detailId);

}