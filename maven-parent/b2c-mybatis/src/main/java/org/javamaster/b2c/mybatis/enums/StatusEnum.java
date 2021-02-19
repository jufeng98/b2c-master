package org.javamaster.b2c.mybatis.enums;

/**
 * Created on 2019/3/6.<br/>
 *
 * @author yudong
 */
public enum StatusEnum implements EnumBase {
    ENABLED(0, "启用"),
    DISABLED(1, "禁用");
    private final int code;
    private final String msg;

    StatusEnum(int code, String msg) {
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

    public static String getMsg(int index) {
        for (StatusEnum c : StatusEnum.values()) {
            if (c.getCode() == index) {
                return c.msg;
            }
        }
        return null;
    }
}
