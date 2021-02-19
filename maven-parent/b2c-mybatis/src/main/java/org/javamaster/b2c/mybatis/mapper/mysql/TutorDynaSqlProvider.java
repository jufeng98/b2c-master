package org.javamaster.b2c.mybatis.mapper.mysql;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * <p>
 * 动态 SQL provider 方法可以接收以下其中一种参数：
 *  无参数
 *  和映射器 Mapper 接口的方法同类型的参数
 *  java.util.Map
 * </p>
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
public class TutorDynaSqlProvider {
    public String findTutorByIdSql(Integer tutorId) {
        return "SELECT TUTOR_ID AS tutorId, NAME, EMAIL FROM TUTORS WHERE TUTOR_ID = " + tutorId;
    }

    public String findTutorByIdSql1(Integer tutorId) {
        return new SQL() {
            {
                SELECT("tutor_id as tutorId, name, email");
                FROM("tutors");
                WHERE("tutor_id=" + tutorId);
            }
        }.toString();
    }

    public String findTutorByNameAndEmailSql(Map<String, Object> map) {
        String name = (String) map.get("param1");
        String email = (String) map.get("param2");
        // you can also get those values using 0,1 keys
        return new SQL() {
            {
                SELECT("tutor_id as tutorId, name, email");
                FROM("tutors");
                WHERE("name=#{param1} AND email=#{param2}");
            }
        }.toString();
    }
}
