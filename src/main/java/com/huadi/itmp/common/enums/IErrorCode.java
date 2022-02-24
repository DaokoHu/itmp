package com.huadi.itmp.common.enums;

/**
 * 错误码接口
 * @author 胡学良
 */
public interface IErrorCode {
    /**
     * 获取错误代码
     * @return code
     */
    long code();

    /**
     * 获取错误信息
     * @return msg
     */
    String msg();

}
