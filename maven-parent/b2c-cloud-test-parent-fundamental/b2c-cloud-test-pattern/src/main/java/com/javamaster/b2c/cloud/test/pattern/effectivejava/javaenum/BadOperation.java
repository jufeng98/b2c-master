package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public enum BadOperation {
	PLUS, MINUS, TIMES, DEVIDE;

	double apply(double a, double b) {
		double sum = 0;
		switch (this) {
		case PLUS:
			sum = a + b;
			break;
		case MINUS:
			sum = a - b;
			break;
		case TIMES:
			sum = a * b;
			break;
		case DEVIDE:
			sum = a / b;
			break;
		default:
			throw new AssertionError();
		}
		return sum;
	}

	public static void main(String[] args) {
              System.out.println(BadOperation.PLUS.apply(23, 23));;
	}
}
