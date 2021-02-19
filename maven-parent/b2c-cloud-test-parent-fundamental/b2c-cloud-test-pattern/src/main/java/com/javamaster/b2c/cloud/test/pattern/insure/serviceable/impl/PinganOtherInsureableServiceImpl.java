package com.javamaster.b2c.cloud.test.pattern.insure.serviceable.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamaster.b2c.cloud.test.pattern.insure.model.Toubao;
import com.javamaster.b2c.cloud.test.pattern.insure.model.PinganDO;
import com.javamaster.b2c.cloud.test.pattern.insure.serviceable.PinganInsureableService;

@Service
public class PinganOtherInsureableServiceImpl implements PinganInsureableService {
	@Autowired
	private PinganDO pinganDO;

	@Override
	public void doInsureablce(Toubao Toubao) throws Exception {
		System.out.println("平安综合险或者退票险或者旅游险投保成功");
	}

	@Override
	public boolean shouldHandler(String type) {
		if (pinganDO.getCombinationMap().get(type) != null || pinganDO.getComplexMap().get(type) != null
				|| pinganDO.getTouristMap().get(type) != null) {
			return true;
		} else {
			return false;
		}
	}
}
