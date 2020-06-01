package com.example.demo.service.impl;

import com.example.demo.entity.TSubscription;
import com.example.demo.dao.TSubscriptionDao;
import com.example.demo.service.TSubscriptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSubscription)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 16:50:32
 */
@Service("tSubscriptionService")
public class TSubscriptionServiceImpl implements TSubscriptionService {
    @Resource
    private TSubscriptionDao tSubscriptionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TSubscription queryById(Long id) {
        return this.tSubscriptionDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TSubscription> queryAllByLimit(int offset, int limit) {
        return this.tSubscriptionDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<TSubscription> queryAll(TSubscription subscription) {
        return this.tSubscriptionDao.queryAll(subscription);
    }

    @Override
    public List<TSubscription> queryAllByUserId(String userId,String type) {
        return this.tSubscriptionDao.queryAllByUserId(userId,type);
    }

    /**
     * 新增数据
     *
     * @param tSubscription 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TSubscription tSubscription) {
        return this.tSubscriptionDao.insert(tSubscription);
    }

    /**
     * 修改数据
     *
     * @param tSubscription 实例对象
     * @return 实例对象
     */
    @Override
    public TSubscription update(TSubscription tSubscription) {
        this.tSubscriptionDao.update(tSubscription);
        return this.queryById(tSubscription.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tSubscriptionDao.deleteById(id) > 0;
    }
}