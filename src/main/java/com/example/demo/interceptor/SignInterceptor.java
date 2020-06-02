package com.example.demo.interceptor;

import cn.hutool.core.text.StrBuilder;
import com.example.demo.base.ApiException;
import com.example.demo.base.ApiResult;
import com.example.demo.base.NormalConstant;
import com.example.demo.base.ResultCodeEnum;
import com.example.demo.utils.MD5Util;
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
public class SignInterceptor implements HandlerInterceptor {


    /**
     * 请求sign校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String[]> treeMap = new TreeMap<>(map);
        String fromSign;
        StrBuilder sb = new StrBuilder();
        if (treeMap.containsKey(NormalConstant.SIGN)) {
            fromSign = treeMap.get(NormalConstant.SIGN)[0];
            treeMap.remove(NormalConstant.SIGN);
        } else {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.SIGN_NULL));
        }
        for (Map.Entry<String, String[]> entry : treeMap.entrySet()) {
            sb.append(entry.getKey());
            for (String val : entry.getValue()) {
                sb.append(val);
            }
        }
        String sign = MD5Util.md5LowerCase(sb + NormalConstant.PRIVATE_KEY);
        if (!sign.equals(fromSign)) {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.SIGN_ERROR));
        }
        return true;
    }
}

