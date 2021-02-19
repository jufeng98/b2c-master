package org.javamaster.b2c.mybatis.enums;

/**
 * Created on 2019/3/6.<br/>
 *
 * @author yudong
 */
public enum DelFlagEnum implements EnumBase {
    ORDINARY(0, "正常"),
    ALREADY_DEL(1, "已删除");
    private final int code;
    private final String msg;

    DelFlagEnum(int code, String msg) {
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
