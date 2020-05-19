package com.example.demo.dao;

import com.example.demo.entity.TComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TComment)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-14 16:50:54
 */
@Mapper
public interface TCommentDao {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TComment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TComment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tComment 实例对象
     * @return 对象列表
     */
    List<TComment> queryAll(TComment tComment);

    /**
     * 通过文章Id查找评论
     * @param articleId
     * @return
     */
    List<TComment> queryAllByArticleId(Integer articleId);

    /**
     * 新增数据
     *
     * @param tComment 实例对象
     * @return 影响行数
     */
    int insert(TComment tComment);

    /**
     * 修改数据
     *
     * @param tComment 实例对象
     * @return 影响行数
     */
    int update(TComment tComment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}