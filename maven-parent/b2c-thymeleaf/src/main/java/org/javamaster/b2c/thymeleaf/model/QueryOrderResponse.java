package org.javamaster.b2c.thymeleaf.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class QueryOrderResponse extends PaginationBean {
	private String errorMsg;
	private List<QueryPlaneOrdersResponse> order;
	private String orderStatusTotal;
	private String changeStatusTotal;
	private String returnStatusTotal;
	private String inviteSegmentNum;
	private String unpayOrderTotal;
	private String yhqNum;

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<QueryPlaneOrdersResponse> getOrder() {
		return order;
	}

	public void setOrder(List<QueryPlaneOrdersResponse> order) {
		this.order = order;
	}

	public String getOrderStatusTotal() {
		return orderStatusTotal;
	}

	public void setOrderStatusTotal(String orderStatusTotal) {
		this.orderStatusTotal = orderStatusTotal;
	}

	public String getChangeStatusTotal() {
		return changeStatusTotal;
	}

	public void setChangeStatusTotal(String changeStatusTotal) {
		this.changeStatusTotal = changeStatusTotal;
	}

	public String getReturnStatusTotal() {
		return returnStatusTotal;
	}

	public void setReturnStatusTotal(String returnStatusTotal) {
		this.returnStatusTotal = returnStatusTotal;
	}

	public String getInviteSegmentNum() {
		return inviteSegmentNum;
	}

	public void setInviteSegmentNum(String inviteSegmentNum) {
		this.inviteSegmentNum = inviteSegmentNum;
	}

	public String getUnpayOrderTotal() {
		return unpayOrderTotal;
	}

	public void setUnpayOrderTotal(String unpayOrderTotal) {
		this.unpayOrderTotal = unpayOrderTotal;
	}

	public String getYhqNum() {
		return yhqNum;
	}

	public void setYhqNum(String yhqNum) {
		this.yhqNum = yhqNum;
	}

}
