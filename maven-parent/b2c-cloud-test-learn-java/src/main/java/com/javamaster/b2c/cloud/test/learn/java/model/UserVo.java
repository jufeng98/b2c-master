package com.javamaster.b2c.cloud.test.learn.java.model;

import cn.com.bluemoon.handypoi.excel.annos.ExcelColumn;
import lombok.Data;

/**
 * @author yudong
 * @date 2020/3/23
 */
@Data
public class UserVo {
    @ExcelColumn(columnName = "id")
    private Integer id;
    @ExcelColumn(columnName = "phone")
    private String phone;
}