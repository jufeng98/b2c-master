package org.javamaster.spring.mybatisplus.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author liangyudong
 * @date 2021/3/19
 */
@Data
public class Actor {
    private Integer actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
}
