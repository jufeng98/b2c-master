package org.javamaster.b2c.mongodb.model;

import java.io.Serializable;

/**
 * @author yudong
 * @date 2020/7/2
 */
public class AggregateValue implements Serializable {
    private Integer id;
    private Long count;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
