package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public enum Planet {
	
	MERCURY(3.303e23,2.439e6),
	VENUS(4.869e24,6.053e6),
	EARTH(5.975e24,6.378e6),
	MARS(6.419e23,3.393e6),
	JUPITER(1.899e27,7.149e7),
	SATURN(5.685e26,6.027e7),
	URANUS(8.683e25,2.556e7),
	NEPTUNE(1.024e26,2.477e7);
	
	private final double mass;
	private final double radius;
	private final double surfaceGravity;
	private final double G = 6.673e-11;

	public static void main(String[] args) {
		double earthWeight = 100;
		double mass = earthWeight / Planet.EARTH.surfaceGravity;
		for (Planet p : Planet.values()) {
			System.out.printf("weight on %s is %f\n\r", p, p.surfaceGravity(mass));
		}
	}
	
	private Planet(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		this.surfaceGravity = G * mass / (radius * radius);
	}

	public double surfaceWeight() {
		return mass * surfaceGravity;
	}
	
	public double getMass() {
		return mass;
	}

	public double getRadius() {
		return radius;
	}

	public double surfaceGravity(double mass) {
		return mass * surfaceGravity;
	}

}
