package com.javamaster.b2c.cloud.test.learn.java.model;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * Created on 2019/1/14.<br/>
 *
 * @author yudong
 */
public class AppointmentOrderDetailReqDto implements Serializable {

    private static final long serialVersionUID = -3094748451635821156L;
    /**
     * 预约单号
     */
    @Length(min = 1, max = 32)
    private String appointmentOrderCode;
    /**
     * 洗衣单号
     */
    @Length(min = 1, max = 32)
    private String washOrderCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAppointmentOrderCode() {
        return appointmentOrderCode;
    }

    public void setAppointmentOrderCode(String appointmentOrderCode) {
        this.appointmentOrderCode = appointmentOrderCode;
    }

    public String getWashOrderCode() {
        return washOrderCode;
    }

    public void setWashOrderCode(String washOrderCode) {
        this.washOrderCode = washOrderCode;
    }
}
