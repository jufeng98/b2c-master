package com.javamaster.b2c.cloud.test.learn.java.jdbc.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.DatabaseService;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Column;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Database;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Table;
import com.google.common.collect.Lists;
import com.mysql.jdbc.JDBC4Connection;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/7/9
 */
public class MysqlDatabaseServiceImpl implements DatabaseService {

    private final DatabaseMetaData databaseMetaData;
    private final JdbcTemplate jdbcTemplate;

    @SneakyThrows
    public MysqlDatabaseServiceImpl(DataSource dataSource) {
        JDBC4Connection connection = (JDBC4Connection) ((DruidPooledConnection) dataSource.getConnection()).getConnection();
        connection.setUseInformationSchema(true);
        jdbcTemplate = new JdbcTemplate(dataSource);
        databaseMetaData = connection.getMetaData();
    }

    @Override
    @SneakyThrows
    public Database getDatabase() {
        Database database = new Database();
        database.setUserName(databaseMetaData.getUserName());
        database.setSystemFunctions(databaseMetaData.getSystemFunctions());
        database.setTimeDateFunctions(databaseMetaData.getTimeDateFunctions());
        database.setStringFunctions(databaseMetaData.getStringFunctions());
        database.setSchemaTerm(databaseMetaData.getSchemaTerm());
        database.setUrl(databaseMetaData.getURL());
        database.setReadOnly(databaseMetaData.isReadOnly());
        database.setDatabaseProductName(databaseMetaData.getDatabaseProductName());
        database.setDatabaseProductVersion(databaseMetaData.getDatabaseProductVersion());
        database.setDriverName(databaseMetaData.getDriverName());
        database.setDriverVersion(databaseMetaData.getDriverVersion());
        database.setSupportsGetGeneratedKeys(databaseMetaData.supportsGetGeneratedKeys());
        database.setSupportsGroupBy(databaseMetaData.supportsGroupBy());
        database.setSupportsOuterJoins(databaseMetaData.supportsOuterJoins());
        List<String> tableTypes = Lists.newArrayList();
        ResultSet rs = databaseMetaData.getTableTypes();
        while (rs.next()) {
            tableTypes.add(rs.getString("TABLE_TYPE"));
        }
        database.setTableTypes(tableTypes);
        database.setMaxStatements(databaseMetaData.getMaxStatements());
        return database;
    }

    @Override
    @SneakyThrows
    public List<Table> getTables() {
        String[] types = {"TABLE"};
        return getTypes(types);
    }

    @Override
    @SneakyThrows
    public List<Table> getViews() {
        String[] types = {"VIEW"};
        return getTypes(types);
    }

    @SneakyThrows
    private List<Table> getTypes(String[] types) {
        ResultSet rs = databaseMetaData.getTables(null, null, "%", types);
        List<Table> tables = Lists.newArrayList();
        while (rs.next()) {
            String viewName = rs.getString("TABLE_NAME");
            String remarks = rs.getString("REMARKS");
            remarks = remarks != null ? remarks : "";
            tables.add(new Table(viewName, remarks));
        }
        return tables;
    }

    @Override
    @SneakyThrows
    public List<Column> getTableColumns(String tableName) {
        ResultSet result = databaseMetaData.getPrimaryKeys(null, null, tableName);
        String primaryColumnName = "";
        if (result.next()) {
            primaryColumnName = result.getString("COLUMN_NAME");
        }
        ResultSet rs = databaseMetaData.getColumns(null, null, tableName, null);
        List<Column> columns = Lists.newArrayList();
        while (rs.next()) {
            Column column = new Column();
            String columnName = rs.getString("COLUMN_NAME");
            if (primaryColumnName.equals(columnName)) {
                column.setPrimaryKey(true);
            }
            column.setName(columnName);
            int dataType = rs.getInt("DATA_TYPE");
            column.setType(dataType);
            String dataTypeName = rs.getString("TYPE_NAME");
            column.setTypeName(dataTypeName);
            int columnSize = rs.getInt("COLUMN_SIZE");
            column.setSize(columnSize);
            int decimalDigits = rs.getInt("DECIMAL_DIGITS");
            column.setDigits(decimalDigits);
            int numPrecRadix = rs.getInt("NUM_PREC_RADIX");
            column.setPrecRadix(numPrecRadix);
            int nullAble = rs.getInt("NULLABLE");
            column.setNullable(nullAble);
            String remarks = rs.getString("REMARKS");
            column.setRemarks(remarks);
            String columnDef = rs.getString("COLUMN_DEF");
            column.setDef(columnDef);
            int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");
            column.setCharOctetLength(charOctetLength);
            int ordinalPosition = rs.getInt("ORDINAL_POSITION");
            column.setOrdinalPosition(ordinalPosition);
            String isNullAble = rs.getString("IS_NULLABLE");
            column.setIsNullable(isNullAble);
            String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
            column.setIsAutoincrement(isAutoincrement);
            columns.add(column);
        }
        return columns;
    }

    @Override
    public List<Map<String, Object>> getTableData(String name, int size) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from " + name + " limit ?", size);
        return list;
    }
}
