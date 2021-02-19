package org.javamaster.b2c.mybatistk.enums;

/**
 * Created on 2018/11/15.<br/>
 *
 * @author yudong
 */
public enum StatusEnum {
	
	PREPAID("状态描述一", "prepaid"),
	
	ALIPAY("状态描述二", "alipay"),
	
	WXPAY("状态描述三", "wxpay"),
	
	UNIONPAY("状态描述四", "unionpay"),
	
	WASHCARD("状态描述五", "washcard"),
	
	COD("状态描述六", "cod");
	
	private String name;
	private String code;

	private StatusEnum(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public static String getName(String code) {
		for (StatusEnum orderStatus : StatusEnum.values()) {
			if (orderStatus.getCode().equals(code)) {
				return orderStatus.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
