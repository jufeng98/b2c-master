package com.javamaster.b2c.cloud.test.learn.java.mustache.model;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author yudong
 * @date 2019/7/19
 */
@Data
public class CommonExample {
    protected String packageName;
    protected String author;
    protected String className;
    protected String classComment;
    protected String createDate = DateFormatUtils.format(new Date(), "yyyy/MM/dd");
}
