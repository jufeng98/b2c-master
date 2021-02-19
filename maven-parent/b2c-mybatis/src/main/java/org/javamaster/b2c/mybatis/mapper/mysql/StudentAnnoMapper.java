package org.javamaster.b2c.mybatis.mapper.mysql;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.*;
import org.javamaster.b2c.mybatis.model.*;

import java.util.List;

public interface StudentAnnoMapper {


    @Select(" SELECT STUD_ID AS studId, NAME, EMAIL, DOB, PHONE, GENDER " +
            " FROM STUDENTS " +
            " WHERE STUD_ID = #{Id} ")
    Student findStudentById(Integer id);

    @Select("SELECT * FROM STUDENTS")
    @Results(
            {
                    @Result(id = true, column = "stud_id", property = "studId"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "address_id", property = "address.addressId")
            })
    List<Student> findAllStudents();

    @Select("SELECT ADDRESS_ID AS ADDRID, STREET, CITY, STATE, ZIP, COUNTRY FROM ADDRESSES WHERE ADDRESS_ID=#{id}")
    Address findAddressById(int id);

    @Select("SELECT * FROM STUDENTS WHERE STUD_ID=#{studId} ")
    @Results(
            {
                    @Result(id = true, column = "stud_id", property = "studId"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "email", property = "email"),
                    @Result(property = "address", column = "address_id"
                            , one = @One(select = "org.javamaster.b2c.mybatis.mapper.mysql.StudentAnnoMapper.findAddressById"))
            })
    Student selectStudentWithAddress(int studId);

    @Select("select * from courses where tutor_id=#{tutorId}")
    @Results(
            {
                    @Result(id = true, column = "course_id", property = "courseId"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "description", property = "description"),
                    @Result(column = "start_date", property = "startDate"),
                    @Result(column = "end_date", property = "endDate")
            })
    List<Course> findCoursesByTutorId(int tutorId);

    @Select("SELECT tutor_id, name as tutor_name, email, address_id FROM tutors where tutor_id=#{tutorId}")
    @Results(
            {
                    @Result(id = true, column = "tutor_id", property = "tutorId"),
                    @Result(column = "tutor_name", property = "name"),
                    @Result(column = "email", property = "email"),
                    @Result(property = "address", column = "address_id",
                            one = @One(select = "org.javamaster.b2c.mybatis.mapper.mysql.StudentAnnoMapper.findAddressById")),
                    @Result(property = "courses", column = "tutor_id",
                            many = @Many(select = "org.javamaster.b2c.mybatis.mapper.mysql.StudentAnnoMapper.findCoursesByTutorId"))
            })
    Tutor findTutorById(int tutorId);

    String INSERT_STUDENT_STR = "INSERT INTO STUDENTS(STUD_ID,NAME,EMAIL,ADDRESS_ID, PHONE)" +
            "VALUES(#{studId},#{name},#{email},#{address.addressId},#{phone})";

    @Insert(INSERT_STUDENT_STR)
    @Options(useGeneratedKeys = true, keyProperty = "studId")
    int insertStudent(Student student);

    @Insert(" INSERT INTO STUDENTS(STUD_ID,NAME,EMAIL,ADDRESS_ID, PHONE) " +
            " VALUES (#{studId},#{name},#{email},#{address.addressId},#{phone}) ")
    @SelectKey(statement = "SELECT next_val_func('student_seq')",
            keyProperty = "studId", resultType = int.class, before = true)
    int insertStudent1(Student student);

    @Insert(" INSERT INTO STUDENTS(NAME,EMAIL,ADDRESS_ID, PHONE) " +
            " VALUES (#{name},#{email},#{address.addressId},#{phone}) ")
    @SelectKey(statement = "SELECT cur_val_func('student_seq')",
            keyProperty = "studId", resultType = int.class, before = false)
    int insertStudent2(Student student);

    @Update("UPDATE STUDENTS SET NAME=#{name}, EMAIL=#{email},PHONE=#{phone} " +
            "WHERE STUD_ID=#{studId}")
    int updateStudent(Student student);

    @Delete("DELETE FROM STUDENTS WHERE STUD_ID=#{studId}")
    int deleteStudent(int studId);


    @SelectProvider(type = TutorDynaSqlProvider.class, method = "findTutorByIdSql")
    Tutor findTutorByIdDynamic(Integer tutorId);

    @SelectProvider(type = TutorDynaSqlProvider.class, method = "findTutorByIdSql1")
    Tutor findTutorByIdDynamic1(Integer tutorId);

    @SelectProvider(type = TutorDynaSqlProvider.class, method = "findTutorByNameAndEmailSql")
    Tutor findTutorByNameAndEmailSql(String name, String email);
}
