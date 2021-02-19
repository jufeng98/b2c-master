package com.javamaster.b2c.cloud.test.learn.java.jdbc;

import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Column;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Database;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Table;

import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/7/9
 */
public interface DatabaseService {
    Database getDatabase();

    List<Table> getTables();

    List<Table> getViews();

    List<Column> getTableColumns(String name);

    List<Map<String, Object>> getTableData(String tableName, int size);
}
