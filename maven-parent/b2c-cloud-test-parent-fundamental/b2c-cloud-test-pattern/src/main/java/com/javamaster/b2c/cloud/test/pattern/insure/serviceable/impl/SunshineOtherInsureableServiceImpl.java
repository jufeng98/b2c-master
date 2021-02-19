package com.javamaster.b2c.cloud.test.pattern.insure.serviceable.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamaster.b2c.cloud.test.pattern.insure.model.Toubao;
import com.javamaster.b2c.cloud.test.pattern.insure.model.SunshineDO;
import com.javamaster.b2c.cloud.test.pattern.insure.serviceable.SunshineInsureableService;

@Service
public class SunshineOtherInsureableServiceImpl implements SunshineInsureableService {
	@Autowired
	private SunshineDO sunshineDO;

	@Override
	public void doInsureablce(Toubao Toubao) throws Exception {
		System.out.println("阳光综合险或者退票险或者旅游险投保成功");
	}

	@Override
	public boolean shouldHandler(String type) {
		if (sunshineDO.getComplexMap().get(type) != null || sunshineDO.getRefundMap().get(type) != null
				|| sunshineDO.getTouristMap().get(type) != null) {
			return true;
		} else {
			return false;
		}
	}
}
