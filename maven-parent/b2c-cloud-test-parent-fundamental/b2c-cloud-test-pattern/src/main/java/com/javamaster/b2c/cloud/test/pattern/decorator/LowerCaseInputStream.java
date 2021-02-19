package com.javamaster.b2c.cloud.test.pattern.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseInputStream extends FilterInputStream {
	protected LowerCaseInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int c = super.read();
		return c == -1 ? c : Character.toLowerCase(c);
	}

	@Override
	public int read(byte[] b) throws IOException {
		int length = super.read(b);
		for (int i = 0; i < b.length; i++) {
			byte c = b[i];
			b[i] = (byte) Character.toLowerCase(c);
		}
		return length;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int length = super.read(b, off, len);
		for (int i = 0; i < b.length; i++) {
			byte c = b[i];
			b[i] = (byte) Character.toLowerCase(c);
		}
		return length;
	}

}
