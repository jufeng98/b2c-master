package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Herb {
	public static enum Type {
		ANNUAL, PERENNIAL, BIENNIAL;
	}

	private final String name;
	private final Type type;

	public Herb(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Herb [name=" + name + ", type=" + type + "]";
	}

	public static void main(String[] args) {
		Herb[] garden = new Herb[6];
		garden[0] = new Herb("one planet", Type.ANNUAL);
		garden[1] = new Herb("one planet1", Type.ANNUAL);
		garden[2] = new Herb("two planet", Type.BIENNIAL);
		garden[3] = new Herb("two planet1", Type.BIENNIAL);
		garden[4] = new Herb("multi planet", Type.PERENNIAL);
		garden[5] = new Herb("multi planet1", Type.PERENNIAL);

		EnumMap<Type, Set<Herb>> enumMap = new EnumMap<Type, Set<Herb>>(Type.class);

		Type[] types = Type.values();
		for (Type type : types) {
			enumMap.put(type, new HashSet<>());
		}
		for (Herb herb : garden) {
			enumMap.get(herb.type).add(herb);
		}

		Set<Entry<Type, Set<Herb>>> set = enumMap.entrySet();
		for (Entry<Type, Set<Herb>> entry : set) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
