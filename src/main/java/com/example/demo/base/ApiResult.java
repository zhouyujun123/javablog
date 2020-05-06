package com.example.demo.base;

import lombok.Data;

/**
 * Created by allan on 25/12/2017.
 */
@Data
public class ApiResult<T> {
    private int resultCode;
    private String resultMsg;
    private T data;
    public static ApiResult resultWith(ResultCodeEnum resultCode, Object data) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = resultCode.getResultMsg();
        apiResult.data = data;
        return apiResult;
    }

    public static ApiResult resultWith(ResultCodeEnum resultCode) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = resultCode.getResultMsg();
        return apiResult;
    }

    public static ApiResult resultWith(ResultCodeEnum resultCode, String msg, Object data) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = msg;
        apiResult.data = data;
        return apiResult;
    }

    public static ApiResult errorWith(ResultCodeEnum resultCode) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = resultCode.getResultMsg();
        return apiResult;
    }

    public static ApiResult errorWith(ResultCodeEnum resultCode, String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.resultCode = resultCode.getResultCode();
        apiResult.resultMsg = msg;
        return apiResult;
    }

    public boolean isSuccess() {
        return this.resultCode == ResultCodeEnum.SUCCESS.getResultCode();
    }
}
