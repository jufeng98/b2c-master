package com.javamaster.b2c.cloud.test.pattern.insure.model;

public class Toubao {
	private String type;
	private String orderNo;

	public Toubao() {
	}

	public Toubao(String type, String orderNo) {
		this.type = type;
		this.orderNo = orderNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
