package com.javamaster.b2c.cloud.test.pattern.state.bad;

public class GumballMachine {
	final static int SOLD_OUT = 0;
	final static int NO_QUARTER = 1;
	final static int HAS_QUARTER = 2;
	final static int SOLD = 3;

	private int staue = SOLD_OUT;
	private int count = 0;

	public GumballMachine(int count) {
		this.count = count;
		if (count > 0) {
			staue = NO_QUARTER;
		}
	}

	public void insertQuarter() {
		if (staue == HAS_QUARTER) {
			System.out.println("can't insert another quarter");
		} else if (staue == NO_QUARTER) {
			staue = HAS_QUARTER;
			System.out.println("insert a quarter");
		} else if (staue == SOLD_OUT) {
			System.out.println("the machine sold out");

		} else if (staue == SOLD) {
			System.out.println("wait,we're already giving a gumball");
		}
	}

	public void ejectQuarter() {
		if (staue == HAS_QUARTER) {
			System.out.println(" quarter returned");
			staue = NO_QUARTER;
		} else if (staue == NO_QUARTER) {
			System.out.println("haven't inserted a quarter");
		} else if (staue == SOLD_OUT) {
			System.out.println("haven't inserted a quarter yet");

		} else if (staue == SOLD) {
			System.out.println("sorry,already turned the crank");
		}
	}

	public void turnCrank() {
		if (staue == HAS_QUARTER) {
			System.out.println("get a gumball");
			staue = SOLD;
			dispense();
		} else if (staue == NO_QUARTER) {
			System.out.println("haven't insert a quarter");
		} else if (staue == SOLD_OUT) {
			System.out.println("there are no gumballs");

		} else if (staue == SOLD) {
			System.out.println("turning twice doesn't get another gumball");
		}
	}

	public void dispense() {
		if (staue == SOLD) {
			System.out.println("falling a gumball");
			count--;
			if (count == 0) {
				System.out.println("out of gumballs");
				staue = SOLD_OUT;
			} else {
				staue = NO_QUARTER;
			}
		} else if (staue == NO_QUARTER) {
			System.out.println("need to pay first");
		} else if (staue == SOLD_OUT) {
			System.out.println("no gumball dispensed");
		} else if (staue == HAS_QUARTER) {
			System.out.println("no gumball dispensed");
		}
	}

	@Override
	public String toString() {
		return "GumballMachine [staue=" + staue + ", count=" + count + "]";
	}

}
