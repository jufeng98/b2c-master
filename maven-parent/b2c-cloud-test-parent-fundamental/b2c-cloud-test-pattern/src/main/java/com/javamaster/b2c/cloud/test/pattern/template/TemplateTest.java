package com.javamaster.b2c.cloud.test.pattern.template;

import com.javamaster.b2c.cloud.test.pattern.template.service.CaffeineBaverage;
import com.javamaster.b2c.cloud.test.pattern.template.service.impl.Coffee;
import com.javamaster.b2c.cloud.test.pattern.template.service.impl.Tea;

/**
 * 模版方法模式:在模版方法中定义算法的骨架,变化部分作为回调方法,由子类实现回调方法来重新定义算法的某些步骤,这样可确保算法的结构保持不变
 * 
 * @author yudong
 *
 */
public class TemplateTest {
	public static void main(String[] args) {
		CaffeineBaverage coffee = new Coffee();
		CaffeineBaverage tea = new Tea();
		coffee.prepareRecipe();
		tea.prepareRecipe();
	}
}
