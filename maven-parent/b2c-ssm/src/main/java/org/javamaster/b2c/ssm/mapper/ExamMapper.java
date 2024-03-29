package org.javamaster.b2c.ssm.mapper;


import org.javamaster.b2c.ssm.model.Exam;

import java.util.List;

/**
 * @author yudong
 */
public interface ExamMapper {
    int deleteByPrimaryKey(Long examId);

    int insert(Exam record);

    List<Exam> select(Exam record);

    Exam selectByPrimaryKey(Long examId);

    List<Exam> selectAll();

    List<Exam> selectAllBackup();

    int updateByPrimaryKey(Exam record);
}