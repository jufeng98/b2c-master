package com.javamaster.b2c.cloud.test.pattern.insure.serviceable;

import com.javamaster.b2c.cloud.test.pattern.insure.model.Toubao;

public interface InsureableService {
	void doInsureablce(Toubao Toubao) throws Exception;

	boolean shouldHandler(String type);
}
