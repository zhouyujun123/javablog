package com.example.demo.service;

import com.example.demo.entity.TComment;
import java.util.List;

/**
 * (TComment)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 16:50:54
 */
public interface TCommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TComment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TComment> queryAllByLimit(int offset, int limit);


    List<TComment> queryAllByArticleId(Integer articleId);

    /**
     * 新增数据
     *
     * @param tComment 实例对象
     * @return 实例对象
     */
    int insert(TComment tComment);

    /**
     * 修改数据
     *
     * @param tComment 实例对象
     * @return 实例对象
     */
    TComment update(TComment tComment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}