package com.marubi.security.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel("登录请求模型")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 6991838328668950336L;
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("密码")
    private String username;
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
}
