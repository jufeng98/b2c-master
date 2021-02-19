package org.javamaster.b2c.mybatistk.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.javamaster.b2c.mybatistk.enums.VisibleEnum;

import java.sql.*;

/**
 * Created on 2018/11/15.<br/>
 *
 * @author yudong
 */
public class VisibleEnumHandler extends BaseTypeHandler<VisibleEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, VisibleEnum visibleEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, visibleEnum.getCode());
    }

    @Override
    public VisibleEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        for (VisibleEnum value : VisibleEnum.values()) {
            if (value.getCode().equals(resultSet.getInt(s))) {
                return value;
            }
        }
        throw new SQLException(resultSet.getInt(s) + "");
    }

    @Override
    public VisibleEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        for (VisibleEnum value : VisibleEnum.values()) {
            if (value.getCode().equals(resultSet.getInt(i))) {
                return value;
            }
        }
        throw new SQLException(resultSet.getInt(i) + "");
    }

    @Override
    public VisibleEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        for (VisibleEnum value : VisibleEnum.values()) {
            if (value.getCode().equals(callableStatement.getInt(i))) {
                return value;
            }
        }
        throw new SQLException(callableStatement.getInt(i) + "");
    }
}
