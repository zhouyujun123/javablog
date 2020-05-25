package com.example.demo.dao;

import com.example.demo.entity.TImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TImg)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
@Mapper
public interface TImgDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TImg queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TImg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tImg 实例对象
     * @return 对象列表
     */
    List<TImg> queryAll(TImg tImg);

    /**
     * 新增数据
     *
     * @param tImg 实例对象
     * @return 影响行数
     */
    int insert(TImg tImg);

    /**
     * 修改数据
     *
     * @param tImg 实例对象
     * @return 影响行数
     */
    int update(TImg tImg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}