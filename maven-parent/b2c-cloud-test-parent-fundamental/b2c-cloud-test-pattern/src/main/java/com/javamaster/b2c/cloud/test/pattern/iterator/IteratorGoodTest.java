package com.javamaster.b2c.cloud.test.pattern.iterator;

import java.util.Iterator;

import com.javamaster.b2c.cloud.test.pattern.iterator.client.DinerMenu;
import com.javamaster.b2c.cloud.test.pattern.iterator.client.HouseMenu;
import com.javamaster.b2c.cloud.test.pattern.iterator.model.MenuItem;

/**
 * 迭代器模式:提供一种统一的方法访问聚合对象的元素 ,而又不暴露其内部的结构
 * 
 * @author yudong
 *
 */
public class IteratorGoodTest {

	public static void main(String[] args) {
		DinerMenu dinerMenu = new DinerMenu();
		HouseMenu houseMenu = new HouseMenu();
		Iterator<MenuItem> iterator = dinerMenu.iterator();
		print(iterator);
		Iterator<MenuItem> iterator2 = houseMenu.iterator();
		print(iterator2);
	}

	public static void print(Iterator<MenuItem> iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			System.out.println(menuItem.getName());
		}
	}

}
