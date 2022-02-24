package com.huadi.itmp.core.enums;

import com.huadi.itmp.common.enums.IErrorCode;

/**
 * 授权错误码
 * @author meteor
 */
public enum AuthorizationErrorCode implements IErrorCode {

    /**
     * 没有携带Authorization请求头
     */
    NON_HEADER_AUTHORIZATION(1000, "未找到认证信息"),

    /**
     * Authorization请求头不合法
     */
    ILLEGAL_HEADER_AUTHORIZATION(1001, "非法的认证信息"),

    /**
     * 在请求头Authorization中没有携带token
     */
    NON_ACCESS_TOKEN(1002, "未携带token"),

    /**
     * token验证失败
     */
    SIGN_VERIFY_FAILURE(1003, "token验证失败"),

    /**
     * token验证失败
     */
    REFRESH_TOKEN_VERIFY_FAILURE(1004, "refresh_token验证失败"),

    IDENTITY_INFORMATION(1005, "身份信息不符");

    private long code;
    private String msg;

    AuthorizationErrorCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
