package org.javamaster.b2c.mybatis.enums;

/**
 * Created on 2019/3/6.<br/>
 *
 * @author yudong
 */
public enum ExamStatusEnum implements EnumBase {
    EXAM_NOT_START(0, "未开始"),
    NEED_EXAM(1, "待考试"),
    IN_EXAM(2, "考试中"),
    FINISH_EXAM(3, "已完成");
    private final int code;
    private final String msg;

    ExamStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
