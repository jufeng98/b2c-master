package com.javamaster.b2c.cloud.test.learn.java.enums;

/**
 * Created on 2019/1/18.<br/>
 *
 * @author yudong
 */
public enum TransactionTypeEnum implements EnumBase {
    UNKNOWN(0, ""),
    CASH(1, "现金交易"),
    TICKET(2, "支票交易"),
    ORIGIN(3, "以物易物");

    private final int code;
    private final String msg;

    TransactionTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static TransactionTypeEnum getEnumByCode(int code) {
        for (TransactionTypeEnum value : TransactionTypeEnum.values()) {
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
