package org.javamaster.b2c.mybatis.typehandler;

import org.apache.ibatis.type.*;
import org.javamaster.b2c.mybatis.model.PhoneNumber;

import java.sql.*;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@MappedTypes(PhoneNumber.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class PhoneNumberTypeHandler extends BaseTypeHandler<PhoneNumber> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PhoneNumber parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getAsString());
    }

    @Override
    public PhoneNumber getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        if (s == null) {
            return null;
        }
        return new PhoneNumber(s);
    }

    @Override
    public PhoneNumber getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        if (s == null) {
            return null;
        }
        return new PhoneNumber(s);
    }

    @Override
    public PhoneNumber getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        if (s == null) {
            return null;
        }
        return new PhoneNumber(s);
    }
}
