package com.huadi.itmp.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 认证结果
 * @author meteor
 */
@Data
@ApiModel("认证结果")
public class AuthenticationResultDTO {
    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("角色")
    private Integer role;

    @ApiModelProperty("token")
    private String accessToken;

    @ApiModelProperty("refreshToken")
    private String refreshToken;
}
