package com.huadi.itmp.modules.user.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 胡学良
 * @date 2021-11-08 12:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
