package org.javamaster.b2c.mybatis.dbconfigs;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.*;

/**
 * @author yudong
 */
public class SpecObjectFactory extends DefaultObjectFactory {
	private static final long serialVersionUID = 1L;

	@Override
    public <T> T create(Class<T> type) {
		return super.create(type);
	}

	@Override
	public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
		return super.create(type, constructorArgTypes, constructorArgs);
	}

	@Override
    public void setProperties(Properties properties) {
		super.setProperties(properties);
	}

	@Override
    public <T> boolean isCollection(Class<T> type) {
		return Collection.class.isAssignableFrom(type);
	}
}