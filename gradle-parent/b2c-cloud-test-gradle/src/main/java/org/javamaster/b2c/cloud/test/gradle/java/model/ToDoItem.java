package org.javamaster.b2c.cloud.test.gradle.java.model;

import java.util.Date;

/**
 * @author yudong
 * @date 2019/6/5
 */
public class ToDoItem {

    private String name;
    private Date remindTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Date remindTime) {
        this.remindTime = remindTime;
    }
}
