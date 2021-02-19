package org.javamaster.b2c.swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yudong
 * @date 2019/12/19
 */
@Data
@ApiModel
public class User {
    @ApiModelProperty(name = "用户id", required = true)
    private String userId;
    @ApiModelProperty(name = "用户名", required = true)
    private String username;
    private boolean enabled;
    @ApiModelProperty(name = "描述")
    private String desc;
}
