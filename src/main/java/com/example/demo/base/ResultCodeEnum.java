package com.example.demo.base;

/**
 * @author allan
 * @date 25/12/2017
 */
public enum ResultCodeEnum {
    ERROR(0, "请求失败"),
    ACCOUNT_EXIST(4011, "账户已存在"),
    MAIL_FAIL(4012, "邮件发送失败"),
    SUCCESS(2000, "请求成功"),
    ILLEGAL_STATE(2001, "错误状态"),
    OPERATION_FAILED(4000, "操作失败"),
    NO_AUTHORITY(4001, "没有权限"),
    INVALIDATE_PARAMS(4002, "未传入有效的 appId 和 appKey"),
    LOGIN_FAILED(4003, "登录名或密码错误"),
    LOGIN_SUCCESS(4010, "登陆成功"),
    USER_LOCKED(4004, "该账户已被锁定"),
    EXCEPTION(5000, "系统请求失败"),
    NOT_AUTHORITY(403, "没有访问权限"),
    VERIFY_CODE_FAILED(4004, "验证码有误"),
    UNRECOGNIZED_IDENTITY(4005, "无法识别身份信息"),
    REPEAT_REQUEST(4006, "重复请求"),
    NO_LOW_PRICE(4007, "获取成本价出错"),
    NO_PHONE_INFO(4008, "手机号运营商信息查询失败"),
    EXCHANGE_FAIL(4009, "第三方兑换失败"),

    SIGN_NULL(3000, "签名参数未传"),
    SIGN_FAILED(3001, "签名错误"),
    PRODUCT_NUM_ERROR(60003, "商品数量不正确"),
    PHONE_NUM_ERROR(60002, "用户号位数不正确"),
    //参数错误
    PARAMETER_FAILED(60001, "参数错误");

    private int resultCode;
    private String resultMsg;

    ResultCodeEnum(int resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
