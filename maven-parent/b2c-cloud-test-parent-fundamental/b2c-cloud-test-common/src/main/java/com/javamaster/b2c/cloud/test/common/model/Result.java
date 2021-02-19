package com.javamaster.b2c.cloud.test.common.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @param <T>
 * @author yudong
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -490238572284858235L;
    private boolean success;
    private String errorCode;
    private String errorMsg;
    private T data;
    private Long count;

    public Result() {
        this(false, null, null, null, null);
    }

    public Result(T data) {
        this(true, null, null, data, null);
    }

    public Result(boolean success, T data) {
        this(success, null, null, data, null);
    }

    public Result(boolean success, T data, Long count) {
        this(success, null, null, data, count);
    }

    public Result(T data, Long count) {
        this(true, null, null, data, count);
    }

    public Result(boolean success, String errorCode, String errorMsg) {
        this(success, errorCode, errorMsg, null, null);
    }

    public Result(boolean success, String errorCode, String errorMsg, T data, Long count) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                ", count=" + count +
                '}';
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
