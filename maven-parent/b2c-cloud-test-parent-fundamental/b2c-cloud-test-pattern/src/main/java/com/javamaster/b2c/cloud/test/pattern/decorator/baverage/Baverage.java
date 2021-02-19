/**
 * 
 */
package com.javamaster.b2c.cloud.test.pattern.decorator.baverage;

/**
 * @author yudong
 *
 */
public abstract class Baverage {
	protected String description = "unknown baverage";
	protected double price;

	public String getDescription() {
		return description;
	}

	public abstract double cost();

}
