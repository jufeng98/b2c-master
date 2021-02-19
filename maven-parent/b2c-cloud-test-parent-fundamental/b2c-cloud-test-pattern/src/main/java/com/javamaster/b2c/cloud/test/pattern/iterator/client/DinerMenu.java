package com.javamaster.b2c.cloud.test.pattern.iterator.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.javamaster.b2c.cloud.test.pattern.iterator.model.MenuItem;

public class DinerMenu implements Iterable<MenuItem> {

	List<MenuItem> menuItems;

	public DinerMenu() {
		menuItems = new ArrayList<>();
		menuItems.add(new MenuItem("breakfast", "fried eggs", false, 2.99));
		menuItems.add(new MenuItem("regular", "fresh blueberries", true, 2.99));
		menuItems.add(new MenuItem("waffles", "good health", true, 3.59));
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	@Override
	public Iterator<MenuItem> iterator() {
		return menuItems.iterator();
	}

}
