package com.example.demo.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author allan
 * @date 13/11/2017
 */
@Controller
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult runtimeExceptionHandler(Exception ex) {
        if (ex instanceof ApiException) {
            ApiException apiException = (ApiException) ex;
            // 测试时先打印出来，方便定位bug
            ex.printStackTrace();
            return apiException.getApiResult();
        }
        ex.printStackTrace();
        log.info(ex.getMessage());
        return ApiResult.errorWith(ResultCodeEnum.EXCEPTION, ex.getMessage());
    }
}
