package com.javamaster.b2c.cloud.test.pattern.iterator.client;

import java.util.Iterator;

import com.javamaster.b2c.cloud.test.pattern.iterator.model.MenuItem;

public class HouseMenu implements Iterable<MenuItem> {
	private static final int MAX_ITEMS = 6;
	private MenuItem[] menuItems;
	private int length;

	public HouseMenu() {
		length = 3;
		if (length > MAX_ITEMS) {
			throw new IllegalArgumentException();
		}
		menuItems = new MenuItem[length];
		menuItems[0] = new MenuItem("breakfast", "fried eggs", false, 2.99);
		menuItems[1] = new MenuItem("regular", "fresh blueberries", true, 2.99);
		menuItems[2] = new MenuItem("waffles", "good health", true, 3.59);
	}

	public MenuItem[] getMenuItems() {
		return menuItems;
	}

	@Override
	public Iterator<MenuItem> iterator() {
		return new MenuIterator();
	}

	private class MenuIterator implements Iterator<MenuItem> {
		private int current;

		@Override
		public boolean hasNext() {
			if (current < length) {
				return true;
			}
			return false;
		}

		@Override
		public MenuItem next() {
			if (current == length) {
				throw new IndexOutOfBoundsException("current:" + current);
			}
			return menuItems[current++];
		}

	}

}
