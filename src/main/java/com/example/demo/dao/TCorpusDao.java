package com.example.demo.dao;

import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TCorpus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TCorpus)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-06 21:42:28
 */
@Mapper
public interface TCorpusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TCorpus queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCorpus> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tCorpus 实例对象
     * @return 对象列表
     */
    List<TCorpus> queryAll(TCorpus tCorpus);

    /**
     * 新增数据
     *
     * @param tCorpus 实例对象
     * @return 影响行数
     */
    int insert(TCorpus tCorpus);

    /**
     * 修改数据
     *
     * @param tCorpus 实例对象
     * @return 影响行数
     */
    int update(TCorpus tCorpus);

    List<TCorpus> findLike(FindDTO find);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}