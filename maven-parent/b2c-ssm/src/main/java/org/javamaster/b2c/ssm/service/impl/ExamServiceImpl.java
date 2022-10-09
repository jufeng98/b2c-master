package org.javamaster.b2c.ssm.service.impl;

import cn.com.bluemoon.service.station.api.DubboCommonService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.SneakyThrows;
import org.javamaster.b2c.ssm.mapper.ExamMapper;
import org.javamaster.b2c.ssm.model.Exam;
import org.javamaster.b2c.ssm.service.ExamService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamMapper examMapper;
    @Reference(check = false)
    private DubboCommonService service;
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ServletContext servletContext;

    @SneakyThrows
    @Override
    public List<Exam> getExam(Exam exam) {
        String washCenterInfo = service.findWashCenterInfo("");
        System.out.println(washCenterInfo);
        return examMapper.select(exam);
    }
}
