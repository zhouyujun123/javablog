package com.example.demo.service.impl;

import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dao.TUserDao;
import com.example.demo.entity.TUser;
import com.example.demo.service.TUserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.RedisUtil;
import com.example.demo.vo.LoginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * (TUser)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 17:08:18
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {

    @Resource
    private TUserDao tUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TUser queryById(Integer id) {
        return this.tUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TUser> queryAllByLimit(int offset, int limit) {
        return this.tUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public ApiResult insert(TUser tUser) {
        int peopleNum = tUserDao.hasPeopleRegistered(tUser.getUserName(), tUser.getUserEmail());
        String password;
        if (peopleNum == 0) {
            return ApiResult.errorWith(ResultCodeEnum.ACCOUNT_EXIST);
        } else {
            try {
                password = MD5Util.md5LowerCase(tUser.getUserPsw());
                tUser.setUserPsw(password);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int i = tUserDao.insert(tUser);
            if (i > 0) {
                return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
            } else {
                return ApiResult.errorWith(ResultCodeEnum.ERROR);
            }
        }
    }

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    @Override
    public TUser update(TUser tUser) {
        this.tUserDao.update(tUser);
        return this.queryById(tUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tUserDao.deleteById(id) > 0;
    }

    @Override
    public ApiResult login(String username, String password, HttpServletResponse response) {
        try {
            password = MD5Util.md5LowerCase(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        TUser userPO = tUserDao.hasPeople(username, password);
        if (userPO != null) {
            String token = JwtUtil.sign(System.currentTimeMillis(), userPO.getId().toString());
            LoginVO loginVO = new LoginVO(userPO.getUserName(), userPO.getId().toString());
            response.addHeader("token", token);
            RedisUtil.set(userPO.getId().toString(), token);
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, loginVO);
        }
        throw new ApiException(ApiResult.errorWith(ResultCodeEnum.LOGIN_FAILED));
    }
}