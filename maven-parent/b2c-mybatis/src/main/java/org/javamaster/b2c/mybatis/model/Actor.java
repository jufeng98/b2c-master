package org.javamaster.b2c.mybatis.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author yu
 * @date 2018/4/28
 */
@Data
public class Actor implements Serializable {
    private Integer actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
}
