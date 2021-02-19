package org.javamaster.b2c.mybatis.model;

import lombok.Data;
import org.javamaster.b2c.mybatis.enums.*;

import java.util.Date;

/**
 * 因为在ObjectMapper注册了针对实现了EnumBase接口的枚举类的module，所以枚举字段不需要在指明如何序列化和反序列化，
 * 也就是不需要在加上这两个注解@JsonSerialize，@JsonDeserialize
 *
 * @author yudong
 */
@Data
public class Exam {
    private Long examId;

    private String examCode;

    private String examName;

    private ExamTypeEnum examType;

    private ExamStatusEnum examStatus;

    private Date publishStartTime;

    private Date publishEndTime;

    private Integer scorePoint;

    private DelFlagEnum delFlag;

    private String examExplain;
}