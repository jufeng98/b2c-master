package com.javamaster.b2c.cloud.test.learn.java.model;

import com.javamaster.b2c.cloud.test.learn.java.enums.CarTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.enums.TransactionTypeEnum;
import com.javamaster.b2c.cloud.test.learn.java.json.EnumBaseDeserializer;
import com.javamaster.b2c.cloud.test.learn.java.json.EnumBaseSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by yu on 2018/3/22.
 */
public class Transaction {
    private String type = null;
    @JsonFormat(pattern = "yyyyMMdd HH:mm:ssSSS", locale = "zh_CN", timezone = "GMT+8")
    private Date date = null;
    private Date create = null;
    private TransactionTypeEnum transactionTypeEnum;
    @JsonSerialize(using = EnumBaseSerializer.class)
    private TransactionTypeEnum transactionType;
    @JsonSerialize(using = EnumBaseSerializer.class)
    @JsonDeserialize(using = EnumBaseDeserializer.class)
    private CarTypeEnum carTypeEnum;

    public Transaction() {
    }

    @JsonCreator
    public Transaction(@JsonProperty("transactionType") int tranCode) {
        this.transactionType = TransactionTypeEnum.getEnumByCode(tranCode);
    }

    public Transaction(String type, Date date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionTypeEnum getTransactionTypeEnum() {
        return transactionTypeEnum;
    }

    public void setTransactionTypeEnum(TransactionTypeEnum transactionTypeEnum) {
        this.transactionTypeEnum = transactionTypeEnum;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public CarTypeEnum getCarTypeEnum() {
        return carTypeEnum;
    }

    public void setCarTypeEnum(CarTypeEnum carTypeEnum) {
        this.carTypeEnum = carTypeEnum;
    }
}
