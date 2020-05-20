package com.example.demo.dao;

import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TArticle)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-06 21:29:47
 */
@Mapper
public interface TArticleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TArticle queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TArticle> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tArticle 实例对象
     * @return 对象列表
     */
    List<TArticle> queryAll(TArticle tArticle);

    /**
     * 新增数据
     *
     * @param tArticle 实例对象
     * @return 影响行数
     */
    int insert(TArticle tArticle);

    /**
     * 修改数据
     *
     * @param tArticle 实例对象
     * @return 影响行数
     */
    int update(TArticle tArticle);

    List<TArticle> findLike(FindDTO find);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}