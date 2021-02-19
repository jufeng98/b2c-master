package com.javamaster.b2c.cloud.test.learn.java.model;

import cn.com.bluemoon.handypoi.excel.annos.ExcelColumn;
import lombok.Data;

/**
 * @author yudong
 * @date 2020/3/23
 */
@Data
public class UserAnswerVo {
    @ExcelColumn(columnName = "user_id")
    private Integer userId;
    @ExcelColumn(columnName = "record")
    private String answer;
}