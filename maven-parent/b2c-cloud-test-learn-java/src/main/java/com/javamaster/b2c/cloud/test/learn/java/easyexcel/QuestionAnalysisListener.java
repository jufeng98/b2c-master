package com.javamaster.b2c.cloud.test.learn.java.easyexcel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yudong
 * @date 2019/6/14
 */
public class QuestionAnalysisListener<T> extends AnalysisEventListener<T> {

    List<T> questionVos = new ArrayList<>();

    @Override
    public void invoke(T o, AnalysisContext analysisContext) {
        questionVos.add(o);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("解析完成");
    }

    public List<T> getQuestionVos() {
        return questionVos;
    }
}
