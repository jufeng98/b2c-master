package com.javamaster.b2c.cloud.test.pattern.effectivejava.clone;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> implements Cloneable {
	private Object[] eles;
	private int size = 0;
	private static final int DEFAULT_LENGTH = 16;

	public Stack() {
		this.eles = new Object[DEFAULT_LENGTH];
	}

	public void push(T t) {
		ensureCapacity();
		eles[size++] = t;
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		T t = (T) eles[--size];
		eles[size] = null;
		return t;
	}

	@Override
	protected Stack<T> clone() {
		try {
			@SuppressWarnings("unchecked")
			Stack<T> stack = (Stack<T>) super.clone();
			stack.eles = eles.clone();
			return stack;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	private void ensureCapacity() {
		if (eles.length == size) {
			eles = Arrays.copyOf(eles, size * 2 + 1);
		}
	}
}
