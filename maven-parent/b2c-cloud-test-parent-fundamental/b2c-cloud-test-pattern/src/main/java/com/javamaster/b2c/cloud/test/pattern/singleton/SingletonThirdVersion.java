package com.javamaster.b2c.cloud.test.pattern.singleton;

public class SingletonThirdVersion {
	private volatile static SingletonThirdVersion singleton;

	private SingletonThirdVersion() {
	}

	public static SingletonThirdVersion getSingleton() {
		if (singleton == null) {
			synchronized (SingletonThirdVersion.class) {
				if (singleton == null) {
					singleton = new SingletonThirdVersion();
				}
			}
		}
		return singleton;
	}
}
