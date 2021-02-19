package com.javamaster.b2c.cloud.test.pattern.iterator;

import java.util.List;

import com.javamaster.b2c.cloud.test.pattern.iterator.client.DinerMenu;
import com.javamaster.b2c.cloud.test.pattern.iterator.client.HouseMenu;
import com.javamaster.b2c.cloud.test.pattern.iterator.model.MenuItem;

public class IteratorBadTest {

	public static void main(String[] args) {
		DinerMenu dinerMenu = new DinerMenu();
		HouseMenu houseMenu = new HouseMenu();
		List<MenuItem> menuItems = dinerMenu.getMenuItems();
		for (MenuItem menuItem : menuItems) {
			System.out.println(menuItem.getName());
		}
		MenuItem[] menuItems2 = houseMenu.getMenuItems();
		for (MenuItem menuItem : menuItems2) {
			System.out.println(menuItem.getName());
		}
	}

}
