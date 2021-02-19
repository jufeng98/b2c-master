package org.javamaster.b2c.dubbo.server.api.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Created on 2018/10/11.<br/>
 *
 * @author yudong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2668999377589717337L;
    private Long id;
    @NotNull
    private String username;
    private String password;
    @Min(10)
    @Max(30)
    private Integer age;
}
