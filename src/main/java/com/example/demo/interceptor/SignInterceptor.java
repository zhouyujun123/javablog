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
     * <p>
     * 签名规则 将业务参数的参数名称按照顺序排序(升序)，并加入相应参数值组合成字符串，
     * 如 app_id+123+param1+value1+param2+value2 得到 appId123param1value1param2value2，
     * 然后将得到的字符串⾸尾拼接privateKey得到新的字符串，如privateKey的值为123321，
     * 则新的字符串为:123321appId123param1value1param2value2123321，然后对该字符串进⾏行MD5加密 (小写)，
     * 得到的便是签名参数sign
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
        String sign = MD5Util.md5LowerCase(NormalConstant.PRIVATE_KEY + sb + NormalConstant.PRIVATE_KEY);
        if (!sign.equals(fromSign)) {
            throw new ApiException(ApiResult.errorWith(ResultCodeEnum.SIGN_ERROR));
        }
        return true;
    }
}

