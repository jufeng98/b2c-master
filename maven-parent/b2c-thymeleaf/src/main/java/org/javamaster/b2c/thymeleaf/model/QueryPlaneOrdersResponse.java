package org.javamaster.b2c.thymeleaf.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class QueryPlaneOrdersResponse {
	private String orderno;
	private String bookagent;
	private String bookagentname;
	private String domesticindicate;
	private String ordertype;
	private String status;
	private String totalpaymoney;
	private String totalmileage;
	private String equivfarecurrency;
	private String createdate;
	private String campaignscriptid;
	private String hasdos;
	private List<QuerySegmentsResponse> seginfo;
	private List<QueryPassengersResponse> psginfo;
	private String readonly;
	private String bookuser;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getBookagent() {
		return bookagent;
	}

	public void setBookagent(String bookagent) {
		this.bookagent = bookagent;
	}

	public String getBookagentname() {
		return bookagentname;
	}

	public void setBookagentname(String bookagentname) {
		this.bookagentname = bookagentname;
	}

	public String getDomesticindicate() {
		return domesticindicate;
	}

	public void setDomesticindicate(String domesticindicate) {
		this.domesticindicate = domesticindicate;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalpaymoney() {
		return totalpaymoney;
	}

	public void setTotalpaymoney(String totalpaymoney) {
		this.totalpaymoney = totalpaymoney;
	}

	public String getTotalmileage() {
		return totalmileage;
	}

	public void setTotalmileage(String totalmileage) {
		this.totalmileage = totalmileage;
	}

	public String getEquivfarecurrency() {
		return equivfarecurrency;
	}

	public void setEquivfarecurrency(String equivfarecurrency) {
		this.equivfarecurrency = equivfarecurrency;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCampaignscriptid() {
		return campaignscriptid;
	}

	public void setCampaignscriptid(String campaignscriptid) {
		this.campaignscriptid = campaignscriptid;
	}

	public String getHasdos() {
		return hasdos;
	}

	public void setHasdos(String hasdos) {
		this.hasdos = hasdos;
	}

	public List<QuerySegmentsResponse> getSeginfo() {
		return seginfo;
	}

	public void setSeginfo(List<QuerySegmentsResponse> seginfo) {
		this.seginfo = seginfo;
	}

	public List<QueryPassengersResponse> getPsginfo() {
		return psginfo;
	}

	public void setPsginfo(List<QueryPassengersResponse> psginfo) {
		this.psginfo = psginfo;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getBookuser() {
		return bookuser;
	}

	public void setBookuser(String bookuser) {
		this.bookuser = bookuser;
	}

}
