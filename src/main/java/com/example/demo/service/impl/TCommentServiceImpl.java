package com.example.demo.service.impl;

import com.example.demo.entity.TComment;
import com.example.demo.dao.TCommentDao;
import com.example.demo.service.TCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TComment)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 16:50:54
 */
@Service("tCommentService")
public class TCommentServiceImpl implements TCommentService {
    @Resource
    private TCommentDao tCommentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TComment queryById(Integer id) {
        return this.tCommentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TComment> queryAllByLimit(int offset, int limit) {
        return this.tCommentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tComment 实例对象
     * @return 实例对象
     */
    @Override
    public TComment insert(TComment tComment) {
        this.tCommentDao.insert(tComment);
        return tComment;
    }

    /**
     * 修改数据
     *
     * @param tComment 实例对象
     * @return 实例对象
     */
    @Override
    public TComment update(TComment tComment) {
        this.tCommentDao.update(tComment);
        return this.queryById(tComment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tCommentDao.deleteById(id) > 0;
    }
}