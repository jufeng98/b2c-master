package org.javamaster.b2c.swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yudong
 * @date 2019/12/20
 */
@Data
@ApiModel
public class UserReqVo {

    @NotBlank
    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    private String username;
    @NotBlank
    @ApiModelProperty(value = "密码", example = "admin", required = true)
    private String password;

}
