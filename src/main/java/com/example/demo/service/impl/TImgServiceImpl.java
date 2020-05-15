package com.example.demo.service.impl;

import com.example.demo.entity.TImg;
import com.example.demo.dao.TImgDao;
import com.example.demo.service.TImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TImg)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 16:50:16
 */
@Service("tImgService")
public class TImgServiceImpl implements TImgService {
    @Resource
    private TImgDao tImgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TImg queryById(Integer id) {
        return this.tImgDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TImg> queryAllByLimit(int offset, int limit) {
        return this.tImgDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tImg 实例对象
     * @return 实例对象
     */
    @Override
    public TImg insert(TImg tImg) {
        this.tImgDao.insert(tImg);
        return tImg;
    }

    /**
     * 修改数据
     *
     * @param tImg 实例对象
     * @return 实例对象
     */
    @Override
    public TImg update(TImg tImg) {
        this.tImgDao.update(tImg);
        return this.queryById(tImg.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tImgDao.deleteById(id) > 0;
    }
}