package com.example.demo.base;

import lombok.Getter;

/**
 * @author allan
 * @date 2018-09-14
 */
@Getter
public class ApiException extends RuntimeException {
    private ApiResult apiResult;

    public ApiException(ApiResult apiResult) {
        super(apiResult.getResultMsg());
        this.apiResult = apiResult;
    }
}
