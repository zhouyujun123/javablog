package com.example.demo.interceptor;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.NormalConstant;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.MD5Util;
import com.example.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author mintaoyu
 * Date on 2020-05-05  19:23
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 十分钟 过期时间
     */
    public static final Long EXPIRE_TIME = 600000L;


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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(NormalConstant.TOKEN);
        if (StrUtil.isNotBlank(token)) {
            DecodedJWT jwt = JwtUtil.verity(token);
            if (jwt != null) {
                String userId = jwt.getClaim(NormalConstant.USER_ID).asString();
                String role = jwt.getClaim(NormalConstant.ROLE).asString();
                Long expireTime = jwt.getExpiresAt().getTime();
                // 验证token是否即将过期  key为userId val为token
                boolean expired = expireTime - System.currentTimeMillis() < EXPIRE_TIME;
                if (RedisUtil.exists(userId) && token.equals(RedisUtil.get(userId))) {
                    if (expired) {
                        String newToken = JwtUtil.sign(userId, role);
                        RedisUtil.set(userId, newToken);
                        response.addHeader(NormalConstant.TOKEN, newToken);
                    }
                    request.setAttribute(NormalConstant.USER_ID, userId);
                    request.setAttribute(NormalConstant.ROLE, role);
                    return true;
                }
                throw new ApiException(ApiResult.errorWith(ResultCodeEnum.TOKEN_EXPIRED));
            } else {
                log.info("token错误");
                throw new ApiException(ApiResult.errorWith(ResultCodeEnum.TOKEN_FAILED));
            }
        } else {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.TOKEN_NULL));
        }
    }
}

