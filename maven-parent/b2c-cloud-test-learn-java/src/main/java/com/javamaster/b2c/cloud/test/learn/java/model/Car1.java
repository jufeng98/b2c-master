package com.javamaster.b2c.cloud.test.learn.java.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * @author yudong
 * @date 2020/9/21
 */
@Data
public class Car1 {
    @Expose(serialize = false, deserialize = false)
    private transient String brand;
    @Expose(serialize = true, deserialize = true)
    private int doors;
    @Expose(serialize = true, deserialize = true)
    private Integer seatCounts;
}
