package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public enum BasicOperation implements Operation {
	PLUS("+") {
		@Override
		public double apply(double a, double b) {
			return a + b;
		}
	},
	MINUS("-") {
		@Override
		public double apply(double a, double b) {
			return a - b;
		}
	},
	TIMES("*") {
		@Override
		public double apply(double a, double b) {
			return a * b;
		}
	},
	DEVIDE("/") {
		@Override
		public double apply(double a, double b) {
			return a / b;
		}
	};
	private final String label;

	private BasicOperation(String label) {
		this.label = label;
	}

	public static void main(String[] args) {
		testAll(BasicOperation.class, 23, 23);
	}

	public static <T extends Enum<T> & Operation> void testAll(Class<T> opSet, double a, double b) {
		T[] t = opSet.getEnumConstants();
		for (T t2 : t) {
			System.out.println(t2 + " " + t2.apply(a, b));
		}
	}

	@Override
	public String toString() {
		return getLabel();
	}

	public String getLabel() {
		return label;
	}

}
