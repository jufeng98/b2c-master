/**
 * 
 */
package com.javamaster.b2c.cloud.test.pattern.decorator.condiment;

import com.javamaster.b2c.cloud.test.pattern.decorator.baverage.Baverage;

/**
 * @author yudong
 *
 */
public abstract class Condiment extends Baverage {

	public abstract String getDescription();

	public abstract double cost();

}
