package com.example.demo.interceptor;

import com.example.demo.base.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author mintaoyu
 * Date on 2020-05-21  16:27
 */
@Slf4j
@Component
public class RoleCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RoleCheck roleCheck = method.getAnnotation(RoleCheck.class);
            if (roleCheck != null) {
                //获取方法中设置的权限信息
                String[] roles = roleCheck.roles();
                String userType = (String) request.getAttribute(NormalConstant.ROLE);
                for (String role : roles) {
                    //判断用户是否有权限访问
                    if (role.equals(userType)) {
                        return true;
                    }
                }
                throw new ApiException(ApiResult.errorWith(ResultCodeEnum.NO_AUTHORITY));
            }
            return true;
        }
        return true;
    }

}
