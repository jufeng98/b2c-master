package org.javamaster.b2c.open.feign.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.javamaster.b2c.open.feign.CustomJsonSerializer;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Data
public class Pageable {
    private Integer pageNum;
    private Integer pageSize;
    @JsonSerialize(nullsUsing = CustomJsonSerializer.class)
    private String token;
}
