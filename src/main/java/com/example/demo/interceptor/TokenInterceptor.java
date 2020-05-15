package com.example.demo.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder sb = new StringBuilder(1000);
        //获取请求参数
        Enumeration em = request.getParameterNames();
        JSONObject data = new JSONObject();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            data.put(name,value);
        }
        HandlerMethod h = (HandlerMethod) handler;
        sb .append("-------------------------------------------------------------\n");
        sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
        sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
        sb.append("Params    : ").append(data).append("\n");
        sb.append("URI       : ").append(request.getRequestURI()).append("\n");
        sb.append("URL       : ").append(request.getRequestURL()).append("\n");
        sb .append("-------------------------------------------------------------\n");
        log.info(sb.toString());
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            DecodedJWT jwt = JwtUtil.verity(token);
            if (jwt != null) {
                String userId = jwt.getClaim("userId").asString();
                Long time = jwt.getClaim("time").asLong();
                // 验证token是否即将过期  key为userId val为token
                boolean expired = System.currentTimeMillis() - time > EXPIRE_TIME;
                if (RedisUtil.exists(userId) && token.equals(RedisUtil.get(userId))) {
                    if (expired) {
                        String newToken = JwtUtil.sign(System.currentTimeMillis(), userId);
                        RedisUtil.set(userId, newToken);
                        response.addHeader("token", newToken);
                    }
                    request.setAttribute("userId", userId);
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

