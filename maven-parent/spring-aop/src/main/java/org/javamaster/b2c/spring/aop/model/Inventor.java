package org.javamaster.b2c.spring.aop.model;

import lombok.Data;

import java.util.Date;

/**
 * @author yudong
 * @date 2020/6/5
 */
@Data
public class Inventor {
    private String name;
    private Date time;
    private String serbian;

    public Inventor(String name, String serbian) {
        this.name = name;
        this.serbian = serbian;
    }

    public Inventor(String name, Date time, String serbian) {
        this.name = name;
        this.time = time;
        this.serbian = serbian;
    }
}
