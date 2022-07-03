package org.javamaster.b2c.ssm.model;

import lombok.Data;

import java.util.Date;

/**
 * @author yudong
 */
@Data
public class Exam {
    private Long examId;

    private String examCode;

    private String examName;

    private Integer examType;

    private Integer examStatus;

    private Date publishStartTime;

    private Date publishEndTime;

    private Integer scorePoint;

    private Integer delFlag;

    private String examExplain;
}