package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaannotion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestAnnoProcessor {
	public static void process(Object object) {
		Method[] methods = object.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (!method.isAnnotationPresent(Test.class)) {
				continue;
			}
			Test testAnno = method.getAnnotation(Test.class);
			if (Modifier.isStatic(method.getModifiers())) {
				System.out.println(method.getName() + " cann't be static method");
				continue;
			}
			if (method.getParameterCount() != 0) {
				System.out.println(method.getName() + " cann't have parameters");
				continue;
			}
			try {
				method.invoke(object);
			} catch (InvocationTargetException e) {

				Throwable realException = e.getTargetException();
				Class<? extends Throwable> class1 = testAnno.expectException();

				if (class1 == Error.class) {
					System.err.println(method.getName() + " test failed:" + realException.getClass().getName());
					continue;
				}
				
				if (class1.isInstance(realException)) {
					System.out.println(method.getName() + " test success:" + realException.getClass().getName());
					continue;
				} else {
					System.err.println(method.getName() + " test failed:" + realException.getClass().getName());
					continue;
				}
			} catch (Throwable e) {
				System.err.println(method.getName() + " test failed:" + e.getClass().getName());
				continue;
			}
			System.out.println(method.getName() + " test success");

		}
	}
}
