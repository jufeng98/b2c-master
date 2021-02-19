package com.javamaster.b2c.cloud.test.learn.java.java8.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 该pojo用于充值卡查询
 *
 * @author yudong
 */
@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Card implements Comparable<Card> {
    private String orderNo;
    private String cardvalue;
    private String cardcount;
    private Integer amt;
    // 1 表示奖励明细 0 表示正常明细
    private String detailedtype;
    private java.awt.Color Color;

    public Card() {
    }

    public Card(Integer amt) {
        this.amt = amt;
    }

    @Override
    public int compareTo(Card o) {
        return this.amt - o.amt;
    }

    public boolean isExpensive() {
        return amt > 3;
    }

    @Override
    public String toString() {
        return "Card{" +
                "orderNo='" + orderNo + '\'' +
                ", cardvalue='" + cardvalue + '\'' +
                ", cardcount='" + cardcount + '\'' +
                ", amt='" + amt + '\'' +
                ", detailedtype='" + detailedtype + '\'' +
                ", Color=" + Color +
                '}';
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCardvalue() {
        return cardvalue;
    }

    public void setCardvalue(String cardvalue) {
        this.cardvalue = cardvalue;
    }

    public String getCardcount() {
        return cardcount;
    }

    public void setCardcount(String cardcount) {
        this.cardcount = cardcount;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public String getDetailedtype() {
        return detailedtype;
    }

    public void setDetailedtype(String detailedtype) {
        this.detailedtype = detailedtype;
    }

    public java.awt.Color getColor() {
        return Color;
    }

    public void setColor(java.awt.Color color) {
        Color = color;
    }

}
