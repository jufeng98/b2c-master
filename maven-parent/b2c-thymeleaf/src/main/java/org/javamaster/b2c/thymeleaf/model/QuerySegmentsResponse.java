package org.javamaster.b2c.thymeleaf.model;


import com.alibaba.fastjson.JSONObject;

public class QuerySegmentsResponse {
	private String carrier;
	private String depcode;
	private String arrcode;
	private String depport;
	private String arrport;
	private String flightno;
	private String deptime;
	private String arrtime;
	private String orderno;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDepcode() {
		return depcode;
	}

	public void setDepcode(String depcode) {
		this.depcode = depcode;
	}

	public String getArrcode() {
		return arrcode;
	}

	public void setArrcode(String arrcode) {
		this.arrcode = arrcode;
	}

	public String getDepport() {
		return depport;
	}

	public void setDepport(String depport) {
		this.depport = depport;
	}

	public String getArrport() {
		return arrport;
	}

	public void setArrport(String arrport) {
		this.arrport = arrport;
	}

	public String getFlightno() {
		return flightno;
	}

	public void setFlightno(String flightno) {
		this.flightno = flightno;
	}

	public String getDeptime() {
		return deptime;
	}

	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}

	public String getArrtime() {
		return arrtime;
	}

	public void setArrtime(String arrtime) {
		this.arrtime = arrtime;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

}
