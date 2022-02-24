package com.huadi.itmp.common.form;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author 胡学良
 * @date 2021-08-26 14:27
 **/
@Data
public class PageForm {

    @NotNull(message = "请传入参数page")
    private Integer page;

    @NotNull(message = "请传入参数size")
    private Integer limit;
}
