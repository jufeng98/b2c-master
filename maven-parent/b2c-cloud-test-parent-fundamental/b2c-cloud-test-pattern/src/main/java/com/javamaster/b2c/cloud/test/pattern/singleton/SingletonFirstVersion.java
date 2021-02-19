package com.javamaster.b2c.cloud.test.pattern.singleton;

public class SingletonFirstVersion {
	private static SingletonFirstVersion singleton;

	private SingletonFirstVersion() {
	}

	public static SingletonFirstVersion getSingleton() {
		if (singleton == null) {
			singleton = new SingletonFirstVersion();
		}
		return singleton;
	}
}
