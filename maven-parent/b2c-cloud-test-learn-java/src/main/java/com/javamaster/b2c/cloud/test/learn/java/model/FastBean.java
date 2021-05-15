package com.javamaster.b2c.cloud.test.learn.java.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author yudong
 * @date 2021/5/12
 */
@Data
public class FastBean {
    private String orderCode;
    private Long shopOrderId;
}
