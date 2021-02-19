package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaenum;

public class BadText {
	public static final int STYLE_BOLD = 1 << 0;
	public static final int STYLE_ITALIC = 1 << 1;
	public static final int STYLE_UNDERLINE = 1 << 2;
	public static final int STYLE_STRIKETHROUGH = 1 << 2;

	public void applyStyle(int styles) {

	}

	public static void main(String[] args) {
		BadText text = new BadText();
		text.applyStyle(STYLE_BOLD | STYLE_ITALIC);
	}
}
