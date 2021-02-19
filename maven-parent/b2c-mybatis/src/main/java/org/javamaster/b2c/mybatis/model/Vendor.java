package org.javamaster.b2c.mybatis.model;

public class Vendor {
	private String vendId;
	private String vendName;
	private String vendCountry;
	private String vendState;

	public String getVendId() {
		return vendId;
	}

	public void setVendId(String vendId) {
		this.vendId = vendId;
	}

	public String getVendName() {
		return vendName;
	}

	public void setVendName(String vendName) {
		this.vendName = vendName;
	}

	public String getVendCountry() {
		return vendCountry;
	}

	public void setVendCountry(String vendCountry) {
		this.vendCountry = vendCountry;
	}

	public String getVendState() {
		return vendState;
	}

	public void setVendState(String vendState) {
		this.vendState = vendState;
	}

}
