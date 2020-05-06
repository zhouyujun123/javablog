package com.example.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:23
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    /**
     * 请求token校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        DecodedJWT jwt = JwtUtil.verity(token);
        if (jwt != null) {
            String userId = jwt.getClaim("userId").asString();
            String loginName = jwt.getClaim("loginName").asString();
            System.out.println("user:" + loginName + "/" + "password:" + userId);
            return true;
        } else {
            System.out.println("验证错误");
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.SIGN_FAILED));
        }
    }
}
