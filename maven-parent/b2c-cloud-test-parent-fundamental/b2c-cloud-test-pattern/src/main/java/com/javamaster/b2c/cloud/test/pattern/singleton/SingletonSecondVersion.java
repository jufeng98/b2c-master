package com.javamaster.b2c.cloud.test.pattern.singleton;

public class SingletonSecondVersion {
	private static SingletonSecondVersion singleton;

	private SingletonSecondVersion() {
	}

	public static synchronized SingletonSecondVersion getSingleton() {
		if (singleton == null) {
			singleton = new SingletonSecondVersion();
		}
		return singleton;
	}
}
