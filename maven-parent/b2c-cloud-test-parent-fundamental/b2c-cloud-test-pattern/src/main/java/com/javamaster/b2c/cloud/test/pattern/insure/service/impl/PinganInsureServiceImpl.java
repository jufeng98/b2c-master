package com.javamaster.b2c.cloud.test.pattern.insure.service.impl;

import java.util.Collection;

import com.javamaster.b2c.cloud.test.pattern.insure.serviceable.InsureableService;
import com.javamaster.b2c.cloud.test.pattern.insure.serviceable.PinganInsureableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.javamaster.b2c.cloud.test.pattern.insure.model.InsureBaseDO;
import com.javamaster.b2c.cloud.test.pattern.insure.model.PinganDO;

@Service
public class PinganInsureServiceImpl extends InsureServiceBase {
	@Autowired
	ApplicationContext context;
	@Autowired
	private PinganDO pinganDO;

	@Override
	protected InsureableService fetchProperInsureable(String type) {

		Collection<PinganInsureableService> collection = context.getBeansOfType(PinganInsureableService.class).values();

		for (PinganInsureableService pinganInsureableService : collection) {
			if (pinganInsureableService.shouldHandler(type)) {
				return pinganInsureableService;
			}
		}
		throw new RuntimeException("wrong type:" + type);
	}

	@Override
	public InsureBaseDO getInsureBase() {
		return pinganDO;
	}

}
