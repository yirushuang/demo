package com.wyp.educenter.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录vo
 */

@Data
@ApiModel(value = "登录对象")
public class LoginVo {

    @ApiModelProperty("登录手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;

}
