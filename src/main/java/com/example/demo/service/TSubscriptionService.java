package com.example.demo.service;

import com.example.demo.entity.TSubscription;
import java.util.List;

/**
 * (TSubscription)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 16:50:32
 */
public interface TSubscriptionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TSubscription queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TSubscription> queryAllByLimit(int offset, int limit);


    List<TSubscription> queryAll(TSubscription subscription);

    List<TSubscription> queryAllByUserId(String userId,String type);

    /**
     *
     * @param subscriberId 订阅者Id
     * @param subscribedId 被订阅者Id
     * @param type 类型
     * @return
     */
    boolean isSub(String subscriberId,String subscribedId,String type);


    /**
     * 新增数据
     *
     * @param tSubscription 实例对象
     * @return 实例对象
     */
    int insert(TSubscription tSubscription);

    /**
     * 修改数据
     *
     * @param tSubscription 实例对象
     * @return 实例对象
     */
    TSubscription update(TSubscription tSubscription);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}