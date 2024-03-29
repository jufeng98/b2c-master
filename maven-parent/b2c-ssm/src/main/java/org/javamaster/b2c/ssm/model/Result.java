package org.javamaster.b2c.ssm.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @param <T>
 * @author yudong
 * @date 2019/6/14
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -490238572284858235L;
    private Boolean isSuccess;
    private Integer responseCode;
    private String responseMsg;
    private T data;
    private Long count;
    private String reqId;
    private Boolean encrypt;

    public Result() {
    }

    public Result(T data) {
        this(true, 0, "请求成功", data, null);
    }

    public Result(T data, Long count) {
        this(true, 0, "请求成功", data, count);
    }

    public Result(Integer responseCode, String responseMsg) {
        this(false, responseCode, responseMsg, null, null);
    }

    public Result(Boolean isSuccess, Integer responseCode, String responseMsg, T data, Long count) {
        this.isSuccess = isSuccess;
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
        this.data = data;
        this.count = count;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success(T data, Boolean encrypt) {
        Result<T> result = new Result<>(data);
        result.setEncrypt(encrypt);
        return result;
    }

    public static <T> Result<T> fail(Integer responseCode, String responseMsg) {
        return new Result<>(responseCode, responseMsg);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getData() {
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

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Boolean getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Boolean encrypt) {
        this.encrypt = encrypt;
    }
}
