package org.javamaster.b2c.mybatis.controller;

import org.javamaster.b2c.mybatis.mapper.mysql.ExamMapper;
import org.javamaster.b2c.mybatis.model.Exam;
import org.javamaster.b2c.mybatis.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yudong
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/exam")
public class ExamController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExamMapper examMapper;

    @RequestMapping(value = "/getExam", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Exam>> getExam(@RequestBody Exam exam) {
        logger.info("req exam:{}", exam);
        List<Exam> exams = examMapper.select(exam);
        return new Result<>(exams);
    }

}
