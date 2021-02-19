package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public enum GoodOperation {
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

	private GoodOperation(String label) {
		this.label = label;
	}

	public static void main(String[] args) {
		System.out.println("label " + GoodOperation.PLUS.getLabel() + " " + GoodOperation.PLUS.apply(23, 23));
		;
	}

	public abstract double apply(double a, double b);

	public String getLabel() {
		return label;
	}

}
