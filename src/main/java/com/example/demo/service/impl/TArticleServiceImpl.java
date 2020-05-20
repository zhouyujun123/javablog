package com.example.demo.service.impl;

import com.example.demo.dto.FindDTO;
import com.example.demo.entity.TArticle;
import com.example.demo.dao.TArticleDao;
import com.example.demo.service.TArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TArticle)表服务实现类
 *
 * @author makejava
 * @since 2020-05-06 21:29:48
 */
@Service("tArticleService")
public class TArticleServiceImpl implements TArticleService {
    @Resource
    private TArticleDao tArticleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TArticle queryById(Integer id) {
        return this.tArticleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TArticle> queryAllByLimit(int offset, int limit) {
        return this.tArticleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tArticle 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(TArticle tArticle) {
        if (this.tArticleDao.insert(tArticle)>0){
            return true;
        }
        return false;
    }

    /**
     * 修改数据
     *
     * @param tArticle 实例对象
     * @return 实例对象
     */
    @Override
    public int update(TArticle tArticle) {
        return this.tArticleDao.update(tArticle);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tArticleDao.deleteById(id) > 0;
    }

    @Override
    public List<TArticle> queryAll(TArticle article) {
        return this.tArticleDao.queryAll(article);
    }

    @Override
    public List<TArticle> findLike(FindDTO find) {
        return this.tArticleDao.findLike(find);
    }
}