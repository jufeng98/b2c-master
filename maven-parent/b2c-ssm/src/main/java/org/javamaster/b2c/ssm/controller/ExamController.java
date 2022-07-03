package org.javamaster.b2c.ssm.controller;

import org.javamaster.b2c.ssm.model.Exam;
import org.javamaster.b2c.ssm.model.Result;
import org.javamaster.b2c.ssm.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/getExam", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Exam>> getExam(@RequestBody Exam exam) {
        List<Exam> exams = examService.getExam(exam);
        return new Result<>(exams);
    }

}
