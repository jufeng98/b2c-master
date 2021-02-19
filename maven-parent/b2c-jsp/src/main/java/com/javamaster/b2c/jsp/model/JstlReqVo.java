package com.javamaster.b2c.jsp.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author yudong
 * @date 2020/3/18
 */
@Data
public class JstlReqVo {
    private String id;
    @NotNull
    @Size(min = 3, max = 30, message = "length between 3 and 30")
    private String name;
    private String password;
    private Boolean rememberLogin;
    private List<String> appType;
    private Integer gender;
    private Integer applyStatus;
    private String desc;
    private String productType;
    private String couponType;
    private String label;
}
