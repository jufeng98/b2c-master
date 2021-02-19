package com.javamaster.b2c.cloud.test.pattern.singleton;

/**
 * 确保一个类只有一个实例,并提供一个全局访问点
 * 
 * @author yudong
 *
 */
public class SingletonTest {
	public static void main(String[] args) {
		System.out.println(SingletonFirstVersion.getSingleton());
		System.out.println(SingletonFirstVersion.getSingleton());
		System.out.println(SingletonSecondVersion.getSingleton());
		System.out.println(SingletonSecondVersion.getSingleton());
		System.out.println(SingletonThirdVersion.getSingleton());
		System.out.println(SingletonThirdVersion.getSingleton());
	}
}
