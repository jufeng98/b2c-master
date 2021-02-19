package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public enum GoodPayrollPDaby {
	
	MONDY(PayType.WEEKDAY), 
	TUESDAY(PayType.WEEKDAY), 
	WEDNEDAY(PayType.WEEKDAY), 
	THURSDAY(PayType.WEEKDAY), 
	FRIDAY(PayType.WEEKDAY), 
	SATURDAY(PayType.WENKEND), 
	SUNDAY(PayType.WENKEND);

	private final PayType payType;

	private GoodPayrollPDaby(PayType payType) {
		this.payType = payType;
	}

	public double pay(double hourWorked, double payRate) {
		return payType.pay(hourWorked, payRate);
	}

	public static enum PayType {
		WEEKDAY {
			@Override
			public double pay(double hourWorked, double payRate) {
				double basePay = hourWorked * payRate;
				double overtimePay = hourWorked <= HOURS_PER_SHIFT ? 0 : (hourWorked - HOURS_PER_SHIFT) * payRate / 2;
				return basePay + overtimePay;
			}
		},
		WENKEND {
			@Override
			public double pay(double hourWorked, double payRate) {
				double basePay = hourWorked * payRate;
				double overtimePay = hourWorked * payRate / 2;
				return basePay + overtimePay;
			}
		};
		private static final int HOURS_PER_SHIFT = 8;

		public abstract double pay(double hourWorked, double payRate);
	}

}
