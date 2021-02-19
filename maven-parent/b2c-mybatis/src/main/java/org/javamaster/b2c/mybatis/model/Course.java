package org.javamaster.b2c.mybatis.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Alias("Course")
@Data
public class Course {
    private Integer courseId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer tutorId;
}
