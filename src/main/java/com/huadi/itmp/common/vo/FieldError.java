package com.huadi.itmp.common.vo;

import lombok.Data;

/**
 * 字段错误，前端传过来的值可能通过不了校验规则，这时候可以把出错的字段和错误信息封装成FieldError对象
 * @author meteor
 */
@Data
public class FieldError {

    /**
     * 出错的字段名称
     */
    private String field;

    /**
     * 错误信息
     */
    private String errMsg;

    public FieldError(String field, String errMsg) {
        this.field = field;
        this.errMsg = errMsg;
    }

    public static FieldError of(String field, String errMsg) {
        return new FieldError(field, errMsg);
    }
}
