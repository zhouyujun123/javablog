package com.example.demo.service.impl;

import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dao.LoginDAO;
import com.example.demo.po.UserPO;
import com.example.demo.service.LoginServer;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.MD5Util;
import com.example.demo.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:32
 */
@Service
public class LoginServerImpl implements LoginServer {

    @Autowired
    private LoginDAO loginDAO;

    @Override
    public ApiResult hasPeople(String username, String password) {
        try {
            password = MD5Util.md5LowerCase(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserPO userPO = loginDAO.hasPeople(username, password);
        if (userPO != null) {
            String token = JwtUtil.sign(username, userPO.getId().toString());
            LoginVO loginVO = new LoginVO(token, userPO.getUserName(), userPO.getId().toString());
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS, loginVO);
        }
        throw new ApiException(ApiResult.errorWith(ResultCodeEnum.LOGIN_FAILED));
    }

    @Override
    public ApiResult registered(String username, String password,String mailbox) {
        UserPO userPO = loginDAO.hasPeopleRegistered(username,mailbox);
        if (userPO != null) {
            return ApiResult.errorWith(ResultCodeEnum.ACCOUNT_EXIST);
        } else {
            try {
                password = MD5Util.md5LowerCase(password);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            int i = loginDAO.addAccount(username, password,mailbox);
            if (i > 0) {
                return ApiResult.resultWith(ResultCodeEnum.SUCCESS);
            } else {
                return ApiResult.errorWith(ResultCodeEnum.ERROR);
            }
        }
    }
}
