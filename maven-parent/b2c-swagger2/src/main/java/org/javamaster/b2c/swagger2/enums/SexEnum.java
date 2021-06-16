package org.javamaster.b2c.swagger2.enums;

/**
 * @author yudong
 * @date 2021/6/16
 */
public enum SexEnum implements EnumBase {
    UNKNOWN(0, ""),
    COME_COLLECT(1, "男"),
    EXPRESS(2, "女"),
    ;

    public final Integer code;
    public final String name;

    SexEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getText() {
        return name;
    }
}
