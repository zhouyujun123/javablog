package com.example.demo.service;

import com.example.demo.entity.TImg;
import java.util.List;

/**
 * (TImg)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
public interface TImgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TImg queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TImg> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tImg 实例对象
     * @return 实例对象
     */
    int insert(TImg tImg);

    /**
     * 修改数据
     *
     * @param tImg 实例对象
     * @return 实例对象
     */
    TImg update(TImg tImg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}