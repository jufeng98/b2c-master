package com.javamaster.b2c.cloud.test.learn.java.model;

import java.io.Serializable;

/**
 * E行用户登录信息
 * @author Administrator
 *
 */
public class EUserInformation extends LoginUserInformation implements Serializable{

	private static final long serialVersionUID = -8191715041208933113L;
	/** E行用户id**/
	private String aid;
	/** 绑定的手机号码 **/
	private String cellPhone;
	/** 绑定的邮箱 **/
	private String email;
	/** 认证状态  （Y已认证 N未认证)**/
	private String identifyStatus;
	/** 默认绑定的明珠卡号 **/
	private String bindMemberNo;
	/** svc客户编号 **/
	private String uid;
	/** 默认明珠卡信息  **/
	private MemberInformation bindMemberInfo;
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentifyStatus() {
		return identifyStatus;
	}
	public void setIdentifyStatus(String identifyStatus) {
		this.identifyStatus = identifyStatus;
	}
	public String getBindMemberNo() {
		return bindMemberNo;
	}
	public void setBindMemberNo(String bindMemberNo) {
		this.bindMemberNo = bindMemberNo;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public MemberInformation getBindMemberInfo() {
		return bindMemberInfo;
	}
	public void setBindMemberInfo(MemberInformation bindMemberInfo) {
		this.bindMemberInfo = bindMemberInfo;
	}
}
