package com.javamaster.b2c.cloud.test.learn.java.thinking;

import java.util.LinkedList;

/**
 * @author yu
 */
public class RearExpressionTest {
	public static void main(String[] args) {
		String[] express = new String[] { "4", "2", "*", "8", "2", "2", "*", "/", "+", "5", "3", "4", "*", "+", "-" };
		LinkedList<String> linkedList = new LinkedList<>();
		for (String ex : express) {
			if (ex.matches("\\d*")) {
				linkedList.addFirst(ex);
				continue;
			}
			int a, b;
			switch (ex) {
			case "+":
				a = Integer.parseInt(linkedList.removeFirst());
				b = Integer.parseInt(linkedList.removeFirst());
				linkedList.addFirst(String.valueOf(b + a));
				break;
			case "-":
				a = Integer.parseInt(linkedList.removeFirst());
				b = Integer.parseInt(linkedList.removeFirst());
				linkedList.addFirst(String.valueOf(b - a));
				break;
			case "*":
				a = Integer.parseInt(linkedList.removeFirst());
				b = Integer.parseInt(linkedList.removeFirst());
				linkedList.addFirst(String.valueOf(b * a));
				break;
			case "/":
				a = Integer.parseInt(linkedList.removeFirst());
				b = Integer.parseInt(linkedList.removeFirst());
				linkedList.addFirst(String.valueOf(b / a));
				break;
			default:
				break;

			}
		}
		System.out.println(linkedList);
	}

}
