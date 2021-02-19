package org.javamaster.b2c.springdatajdbc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

/**
 * @author yudong
 * @date 2019/12/13
 */
@Data
@Table("actor")
public class Actor {
    @Id
    @Column("actor_id")
    private Integer actorId;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("last_update")
    private Timestamp lastUpdate;
}
