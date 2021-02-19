package com.javamaster.b2c.jsp.utils;

import com.javamaster.b2c.jsp.common.Global;
import com.javamaster.b2c.jsp.entity.SysDict;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author yudong
 * @date 2020/3/19
 */
public class DictUtils {

    public static List<SysDict> getDictList(String type) {
        String sql = "select * from sys_dict where type=? order by sort";
        JdbcTemplate jdbcTemplate = Global.context.getBean(JdbcTemplate.class);
        return jdbcTemplate.query(sql, new Object[]{type}, DictUtils::convert);
    }

    @SneakyThrows
    private static SysDict convert(ResultSet rs, int rowNum) {
        SysDict sysDict = new SysDict();
        sysDict.setId(rs.getString("id"));
        sysDict.setValue(rs.getString("value"));
        sysDict.setLabel(rs.getString("label"));
        sysDict.setType(rs.getString("type"));
        sysDict.setSort(rs.getInt("sort"));
        sysDict.setParentId(rs.getString("parent_id"));
        return sysDict;
    }

}
