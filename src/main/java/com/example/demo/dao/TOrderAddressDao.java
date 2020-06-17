package com.example.demo.dao;

import com.example.demo.entity.TOrderAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 收货地址表(TOrderAddress)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-17 13:53:43
 */
@Mapper
public interface TOrderAddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrderAddress queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrderAddress> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tOrderAddress 实例对象
     * @return 对象列表
     */
    List<TOrderAddress> queryAll(TOrderAddress tOrderAddress);

    /**
     * 新增数据
     *
     * @param tOrderAddress 实例对象
     * @return 影响行数
     */
    int insert(TOrderAddress tOrderAddress);

    /**
     * 修改数据
     *
     * @param tOrderAddress 实例对象
     * @return 影响行数
     */
    int update(TOrderAddress tOrderAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}