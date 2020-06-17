package com.example.demo.service.impl;

import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dao.TOrderAddressDao;
import com.example.demo.entity.TOrderAddress;
import com.example.demo.service.TOrderAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址表(TOrderAddress)表服务实现类
 *
 * @author makejava
 * @since 2020-06-17 14:23:10
 */
@Service("tOrderAddressService")
public class TOrderAddressServiceImpl implements TOrderAddressService {
    @Resource
    private TOrderAddressDao tOrderAddressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TOrderAddress queryById(Integer id) {
        return this.tOrderAddressDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TOrderAddress> queryAllByLimit(int offset, int limit) {
        return this.tOrderAddressDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tOrderAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderAddress insert(TOrderAddress tOrderAddress) {
        this.tOrderAddressDao.insert(tOrderAddress);
        return this.queryById(tOrderAddress.getId());
    }

    /**
     * 修改数据
     *
     * @param tOrderAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TOrderAddress update(TOrderAddress tOrderAddress) {
        this.tOrderAddressDao.update(tOrderAddress);
        return this.queryById(tOrderAddress.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public ApiResult deleteById(Integer id) {
        boolean b = this.tOrderAddressDao.deleteById(id) > 0;
        if (b) {
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
        } else {
            return ApiResult.errorWith(ResultCodeEnum.ERROR);
        }
    }
}