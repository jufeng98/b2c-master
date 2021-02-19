package com.javamaster.b2c.cloud.test.learn.java.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created on 2019/3/22.<br/>
 *
 * @author yudong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private int salary;

    private Boolean knowledgeFinish;
    private Date opTime;
    private Date finishTime;
}
