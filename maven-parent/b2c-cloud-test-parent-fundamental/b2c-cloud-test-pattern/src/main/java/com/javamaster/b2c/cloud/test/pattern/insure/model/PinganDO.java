package com.javamaster.b2c.cloud.test.pattern.insure.model;

import org.springframework.stereotype.Component;

@Component
public class PinganDO extends InsureBaseDO {

	@Override
	protected String companyFlag() {
		return "PINGAN";
	}

}
