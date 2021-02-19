package org.javamaster.b2c.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Alias("Tutor")
@Data
public class Tutor {
    private Integer tutorId;
    private String name;
    private String email;
    private Address address;
    private List<Course> courses;
}
