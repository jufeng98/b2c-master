package org.javamaster.b2c.mybatistk.enums;

/**
 * Created on 2018/11/15.<br/>
 *
 * @author yudong
 */
public enum VisibleEnum {

    SHOW(1, "展示"),
    HIDE(0, "隐藏");

    private Integer code;
    private String msg;

    VisibleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
