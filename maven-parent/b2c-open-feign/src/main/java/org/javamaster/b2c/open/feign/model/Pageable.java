package org.javamaster.b2c.open.feign.model;

import lombok.Data;

/**
 * @author yudong
 * @date 2020/7/31
 */
@Data
public class Pageable {
    private Integer pageNum;
    private Integer pageSize;
}
