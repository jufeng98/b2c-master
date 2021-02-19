package com.javamaster.b2c.cloud.test.learn.java.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * @author yudong
 * @date 2019/6/14
 */
public class QuestionReadVo extends BaseRowModel {

    @ExcelProperty(value = "编号")
    private int num;
    @ExcelProperty(value = "题型")
    private String type;
    @ExcelProperty(value = "考点")
    private String point;
    @ExcelProperty(value = "题目内容")
    private String content;
    @ExcelProperty(value = "可选项")
    private String availableOption;
    @ExcelProperty(value = "答案")
    private String answer;
    @ExcelProperty(value = "解析")
    private String analysis;
    @ExcelProperty(value = "是否适用于直播")
    private String broadcast;
    @ExcelProperty(value = "日期", format = "yyyy/MM/dd HH:mm")
    private Date date;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvailableOption() {
        return availableOption;
    }

    public void setAvailableOption(String availableOption) {
        this.availableOption = availableOption;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
