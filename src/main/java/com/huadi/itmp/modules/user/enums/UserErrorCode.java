package com.huadi.itmp.modules.user.enums;

import com.huadi.itmp.common.enums.IErrorCode;

/**
 * @author 胡学良
 * @date 2021-11-08 11:20
 **/
public enum UserErrorCode implements IErrorCode {
    /**
     * 用户信息不存在
     */
    USER_NOT_FOUND(2001, "用户不存在"),

    /**
     * 用户密码错误
     */
    PASSWORD_ERROR(2002, "密码错误");


    UserErrorCode(long code, String msg) {
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

    private final long code;

    private final String msg;
}
