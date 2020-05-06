package com.example.demo.server.impl;

import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.dao.LoginDAO;
import com.example.demo.po.UserPO;
import com.example.demo.server.LoginServer;
import com.example.demo.utils.JwtUtil;
import com.example.demo.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        UserPO userPO = loginDAO.hasPeople(username, password);
        if (userPO != null) {
            String token = JwtUtil.sign(username, userPO.getId().toString());
            LoginVO loginVO = new LoginVO(token,userPO.getUserName(),userPO.getId().toString());
            return ApiResult.resultWith(ResultCodeEnum.SUCCESS,loginVO);
        }
        throw new ApiException(ApiResult.errorWith(ResultCodeEnum.LOGIN_FAILED));
    }
}
