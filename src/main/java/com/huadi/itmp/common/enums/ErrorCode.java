package com.huadi.itmp.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author 胡学良
 * @date 2021-08-26 14:24
 **/
@AllArgsConstructor
public enum ErrorCode implements IErrorCode{
    /**
     * 操作错误
     */
    OPERATION_FAILED(-1, "操作失败！"),

    /**
     * 参数校验错误
     * 前端传进来的参数没有通过校验规则
     */
    FIELD_CHECK_FAILURE(-2, "参数校验失败！"),
    DUPLICATE_SUBMISSION(-3, "访问太频繁");

    private Integer code;
    private String msg;

    @Override
    public long code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
