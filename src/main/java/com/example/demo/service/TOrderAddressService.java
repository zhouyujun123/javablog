package com.example.demo.service;

import com.example.demo.base.ApiResult;
import com.example.demo.entity.TOrderAddress;
import java.util.List;

/**
 * 收货地址表(TOrderAddress)表服务接口
 *
 * @author makejava
 * @since 2020-06-17 14:23:10
 */
public interface TOrderAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrderAddress queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrderAddress> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tOrderAddress 实例对象
     * @return 实例对象
     */
    TOrderAddress insert(TOrderAddress tOrderAddress);

    /**
     * 修改数据
     *
     * @param tOrderAddress 实例对象
     * @return 实例对象
     */
    TOrderAddress update(TOrderAddress tOrderAddress);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    ApiResult deleteById(Integer id);

}