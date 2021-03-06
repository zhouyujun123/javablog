package com.example.demo.conf;

import com.example.demo.interceptor.HeaderInterceptor;
import com.example.demo.interceptor.RoleCheckInterceptor;
import com.example.demo.interceptor.SignInterceptor;
import com.example.demo.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author ymt
 */
@Configuration
@Profile({"prod"})
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    TokenInterceptor tokenInterceptor;

    @Autowired
    HeaderInterceptor headerInterceptor;

    @Autowired
    RoleCheckInterceptor roleCheckInterceptor;

    @Autowired
    SignInterceptor signInterceptor;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有的请求
        registry.addInterceptor(headerInterceptor).addPathPatterns("/**");
//        InterceptorRegistration sign = registry.addInterceptor(signInterceptor).addPathPatterns("/**");
//        sign.excludePathPatterns("/csrf");
//        sign.excludePathPatterns("/");
//        sign.excludePathPatterns("/error");
//        sign.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        InterceptorRegistration interceptorToken = registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
        // 主页
        interceptorToken.excludePathPatterns("/login");
        interceptorToken.excludePathPatterns("/registered");
        interceptorToken.excludePathPatterns("/getCaptcha");
        interceptorToken.excludePathPatterns("/csrf");
        interceptorToken.excludePathPatterns("/");
        interceptorToken.excludePathPatterns("/error");
        interceptorToken.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        registry.addInterceptor(roleCheckInterceptor).addPathPatterns("/**");
    }



}
