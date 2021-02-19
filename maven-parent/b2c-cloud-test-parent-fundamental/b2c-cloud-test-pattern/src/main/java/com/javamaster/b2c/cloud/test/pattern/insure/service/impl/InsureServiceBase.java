package com.javamaster.b2c.cloud.test.pattern.insure.service.impl;

import com.javamaster.b2c.cloud.test.pattern.insure.model.Toubao;
import com.javamaster.b2c.cloud.test.pattern.insure.service.InsureService;
import com.javamaster.b2c.cloud.test.pattern.insure.serviceable.InsureableService;

/**
 * 工厂方法模式
 * 
 * @author yudong
 *
 */
public abstract class InsureServiceBase implements InsureService {

	@Override
	public void insure(String type, Toubao toubao) throws Exception {
		InsureableService insureableService = fetchProperInsureable(type);
		insureableService.doInsureablce(toubao);

	}

	protected abstract InsureableService fetchProperInsureable(String type);

}
