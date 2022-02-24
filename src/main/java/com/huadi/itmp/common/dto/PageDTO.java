package com.huadi.itmp.common.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.huadi.itmp.common.enums.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 胡学良
 * @date 2021-08-26 14:20
 **/
@Data
public class PageDTO<T> {

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
     * 总页数
     */
    private long count;

    /**
     * 数据
     */
    private List<T> data;

    public PageDTO() {
    }


    public PageDTO (IPage<T> page) {
        setCode(STATUS_SUCCESS_CODE);
        setMsg(STATUS_SUCCESS_MSG);
        setCount(page.getTotal());
        setData(page.getRecords());
    }
    public PageDTO (Integer total,List<T> records) {
        setCode(STATUS_SUCCESS_CODE);
        setMsg(STATUS_SUCCESS_MSG);
        setCount(total);
        setData(records);
    }

    public static PageDTO error(){
        return new PageDTO(ErrorCode.OPERATION_FAILED.code(), ErrorCode.OPERATION_FAILED.msg());

    }

    /**
     * 只有响应码和响应信息的构造函数
     * @param code
     * @param msg
     */
    public PageDTO(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
