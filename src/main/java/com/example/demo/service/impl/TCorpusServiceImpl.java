package com.example.demo.service.impl;

import com.example.demo.entity.TCorpus;
import com.example.demo.dao.TCorpusDao;
import com.example.demo.service.TCorpusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TCorpus)表服务实现类
 *
 * @author makejava
 * @since 2020-05-06 21:42:28
 */
@Service("tCorpusService")
public class TCorpusServiceImpl implements TCorpusService {
    @Resource
    private TCorpusDao tCorpusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TCorpus queryById(Integer id) {
        return this.tCorpusDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TCorpus> queryAllByLimit(int offset, int limit) {
        return this.tCorpusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tCorpus 实例对象
     * @return 实例对象
     */
    @Override
    public Boolean insert(TCorpus tCorpus) {
        if (this.tCorpusDao.insert(tCorpus)>0){
            return true;
        }
        return false;
    }

    /**
     * 修改数据
     *
     * @param tCorpus 实例对象
     * @return 实例对象
     */
    @Override
    public TCorpus update(TCorpus tCorpus) {
        this.tCorpusDao.update(tCorpus);
        return this.queryById(tCorpus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tCorpusDao.deleteById(id) > 0;
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<TCorpus> queryAll(TCorpus tCorpus) {
        return this.tCorpusDao.queryAll(tCorpus);
    }
}