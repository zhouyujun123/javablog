package com.example.demo.base;

import com.example.demo.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author ymt
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    TokenInterceptor tokenInterceptor;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有的请求
        InterceptorRegistration interceptor = registry.addInterceptor(tokenInterceptor);
        interceptor.addPathPatterns("/**");
        // 主页
        interceptor.excludePathPatterns("/login");
        interceptor.excludePathPatterns("/registered");
        interceptor.excludePathPatterns("/getCaptcha");
        interceptor.excludePathPatterns("/csrf");
        interceptor.excludePathPatterns("/");
        interceptor.excludePathPatterns("/error");
        interceptor.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("access-control-allow-headers", "access-control-allow-methods", "access-control-allow-origin", "access-control-max-age", "X-Frame-Options", "token")
                .allowCredentials(false).maxAge(6000);
    }


}
