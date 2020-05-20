package com.example.demo.service;

import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TUser;

import java.util.List;

/**
 * (TArticle)表服务接口
 *
 * @author makejava
 * @since 2020-05-06 21:29:47
 */
public interface TArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TArticle queryById(Integer id);

    /**
     * 查询所有数据
     * @return
     */
    List<TArticle> queryAll(TArticle article);

    List<TArticle> findLike(FindDTO find);


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TArticle> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tArticle 实例对象
     * @return 实例对象
     */
    boolean insert(TArticle tArticle);

    /**
     * 修改数据
     *
     * @param tArticle 实例对象
     * @return 实例对象
     */
    int update(TArticle tArticle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}