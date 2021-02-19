package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public enum BadPayrollPDaby {
	MONDY, TUESDAY, WEDNEDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	private static final int HOURS_PER_SHIFT = 8;

	double pay(double hourWorked, double payRate) {
		double basePay = hourWorked * payRate;
		double overtimePay;
		switch (this) {
		case SATURDAY:
		case SUNDAY:
			overtimePay = hourWorked * payRate / 2;
			break;
		default:
			overtimePay = hourWorked <= HOURS_PER_SHIFT ? 0 : (hourWorked - HOURS_PER_SHIFT) * payRate / 2;
			break;
		}
		return basePay + overtimePay;
	}
}
