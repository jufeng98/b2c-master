package org.javamaster.b2c.mybatis.enums;

import java.util.*;

/**
 * Created on 2019/3/6.<br/>
 *
 * @author yudong
 */
public enum OperationTypeEnum implements EnumBase {
    UNKNOWN(0, ""),
    WASHING(1, "洗衣实操"),
    HOUSE_CLEANING(2, "家清实操");
    private final int code;
    private final String msg;

    private static Map<String, OperationTypeEnum> map;
    private static Map<Integer, OperationTypeEnum> map1;

    static {
        map = new HashMap<>(3, 1);
        map1 = new HashMap<>(3, 1);
        for (OperationTypeEnum value : OperationTypeEnum.values()) {
            map.put(value.getMsg(), value);
            map1.put(value.getCode(), value);
        }
    }

    OperationTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static OperationTypeEnum getEnumByMsg(String msg) {
        return Optional.ofNullable(map.get(msg)).orElse(UNKNOWN);
    }

    public static OperationTypeEnum getEnumByCode(Integer code) {
        return Optional.ofNullable(map1.get(code)).orElse(UNKNOWN);
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
