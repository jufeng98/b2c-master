package com.javamaster.b2c.cloud.test.learn.java.enums;

/**
 * Created on 2019/1/18.<br/>
 *
 * @author yudong
 */
public enum CarTypeEnum implements EnumBase {

    UNKNOWN(0, ""),
    BMW(1, "宝马"),
    AO_DI(2, "奥迪"),
    BENZ(3, "奔驰");
    private final int code;
    private final String msg;

    CarTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CarTypeEnum getEnumByCode(int code) {
        for (CarTypeEnum value : CarTypeEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return UNKNOWN;
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
