package com.javamaster.b2c.cloud.test.pattern.effectivejava.composite;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InstrumentHashSet<E> extends HashSet<E> {

	private int addCount = 0;
	private static final long serialVersionUID = 1L;

	public InstrumentHashSet() {
		super();
	}

	public InstrumentHashSet(Collection<? extends E> c) {
		super(c);
	}

	public static void main(String[] args) {
		InstrumentHashSet<String> set = new InstrumentHashSet<>();
		set.addAll(Arrays.asList("a", "b", "f"));
		System.out.println(set.getAddCount());
	}

	@Override
	public boolean add(E e) {
		// addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

}
