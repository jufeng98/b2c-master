package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

import java.util.EnumSet;
import java.util.Set;

public class GoodText {

	public static enum Style {
		STYLE_BOLD, STYLE_ITALIC, STYLE_UNDERLINE, STYLE_STRIKETHROUGH;

	}

	public void applyStyle(Set<Style> styles) {
	}

	public static void main(String[] args) {
		GoodText text = new GoodText();
		text.applyStyle(EnumSet.of(Style.STYLE_BOLD, Style.STYLE_ITALIC));
	}
}
