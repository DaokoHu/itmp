package com.huadi.itmp.core.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huadi.itmp.common.enums.ErrorCode;
import com.huadi.itmp.common.enums.IErrorCode;
import com.huadi.itmp.common.vo.FieldError;
import com.huadi.itmp.core.exception.ServiceException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Restful Api 响应，可以用来包装请求结果
 * @author meteor
 */
@ToString
@Data
@ApiModel("RestfulApi返回结果包装器")
public class Response<T> {

    private static final long STATUS_SUCCESS_CODE = 0;
    private static final String STATUS_SUCCESS_MSG = "操作成功";

    /**
     * 返回状态
     */
    @ApiModelProperty("操作状态")
    private long code;

    /**
     * 返回信息
     */
    @ApiModelProperty("返回信息")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty("数据")
    @JsonInclude(NON_NULL)
    private T data;

    /**
     * 全参构造函数
     * @param code
     * @param msg
     * @param data
     */
    public Response(long code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    /**
     * 只有响应码和响应信息的构造函数
     * @param code
     * @param msg
     */
    public Response(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 带数据的成功返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(STATUS_SUCCESS_CODE, STATUS_SUCCESS_MSG, data);
    }

    /**
     * 不带数据的成功返回
     * @param <T>
     * @return
     */
    public static <T> Response<T> success() {
        return new Response<>(STATUS_SUCCESS_CODE, STATUS_SUCCESS_MSG);
    }

    /**
     * 自定义状态的失败返回
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(IErrorCode errorCode) {
        return new Response<>(errorCode.code(), errorCode.msg());
    }

    /**
     * 自定义状态的失败返回
     * @return
     */
    public static  Response error(long code, String msg) {
        return new Response(code, msg);
    }

    /**
     * 将业务层异常转化为失败信息返回
     * @return
     */
    public static  Response error(ServiceException serviceException) {
        return new Response(serviceException.getErrorCode(), serviceException.getErrorMsg());
    }

    /**
     * 默认状态的失败返回
     * @param <T>
     * @return
     */
    public static <T> Response<T> error() {
        return new Response<>(ErrorCode.OPERATION_FAILED.code(), ErrorCode.OPERATION_FAILED.msg());
    }

    /**
     * 传参错误
     * @return
     */
    public static Response<FieldError> fieldError(String field, String errorMsg) {
        return new Response<>(ErrorCode.FIELD_CHECK_FAILURE.code(), ErrorCode.FIELD_CHECK_FAILURE.msg(), FieldError.of(field, errorMsg));
    }

    /**
     * 传参错误
     * @return
     */
    public static Response<FieldError> fieldError(org.springframework.validation.FieldError fieldError) {
        return fieldError(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
