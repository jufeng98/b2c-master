package com.javamaster.b2c.cloud.test.pattern.insure.service;

import com.javamaster.b2c.cloud.test.pattern.insure.model.Toubao;
import com.javamaster.b2c.cloud.test.pattern.insure.model.InsureBaseDO;

public interface InsureService {
	void insure(String type, Toubao toubao) throws Exception;

	InsureBaseDO getInsureBase();
}
