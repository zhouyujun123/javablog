package com.example.demo.service;

import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.entity.TCorpus;
import java.util.List;

/**
 * (TCorpus)表服务接口
 *
 * @author makejava
 * @since 2020-05-06 21:42:28
 */
public interface TCorpusService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TCorpus queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCorpus> queryAllByLimit(int offset, int limit);




    /**
     * 查询所有数据
     * @return
     */
    List<TCorpus> queryAll(TCorpus tCorpus);

    /**
     * 新增数据
     *
     * @param tCorpus 实例对象
     * @return 实例对象
     */
    Boolean insert(TCorpus tCorpus);

    /**
     * 修改数据
     *
     * @param tCorpus 实例对象
     * @return 实例对象
     */
    TCorpus update(TCorpus tCorpus);

    List<TCorpus> findLike(FindDTO find);


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}