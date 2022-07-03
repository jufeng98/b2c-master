package org.javamaster.b2c.ssm.service;

import org.javamaster.b2c.ssm.model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getExam(Exam exam);
}
