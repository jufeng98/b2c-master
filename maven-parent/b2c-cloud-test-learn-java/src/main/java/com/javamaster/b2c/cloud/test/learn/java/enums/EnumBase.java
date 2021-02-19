package com.javamaster.b2c.cloud.test.learn.java.enums;

/**
 * <p>枚举类的公共接口</p>
 * Created on 2019/1/8.<br/>
 *
 * @author yudong
 */
public interface EnumBase {
    int getCode();

    String getMsg();

    /**
     * 根据code获取对应的枚举对象
     *
     * @param enumClass
     * @param code
     * @param <E>
     * @return 若无对应的枚举对象, 返回null
     */
    static <E extends Enum<?> & EnumBase> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    /**
     * 根据msg获取对应的枚举对象
     *
     * @param enumClass
     * @param msg
     * @param <E>
     * @return 若无对应的枚举对象, 返回null
     */
    static <E extends Enum<?> & EnumBase> E msgOf(Class<E> enumClass, String msg) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getMsg().equals(msg)) {
                return e;
            }
        }
        return null;
    }
}

