package com.javamaster.b2c.cloud.test.pattern.effectivejava.javaannotion;


public class TestController {

	public static void main(String[] args) {
		TestController testController = new TestController();
		TestAnnoProcessor.process(testController);
	}

	@Test
	public void testSuccessNoException() {
		int a = 1;
		int b = 1;
        if (a == b) {

        }
    }

	@Test(expectException = RuntimeException.class)
	public void testSuccessWithExpectedException() throws Exception {
		int a = 1;
		int b = 1;
		if (a == b) {
			throw new RuntimeException();
		}
	}

	@Test(expectException = RuntimeException.class)
	public void testFailWithUnexpectedException() throws Exception {
		int a = 1;
		int b = 1;
		if (a == b) {
			throw new Exception();
		}
	}
	@Test
	public void testFailWithDefault() throws Exception {
		int a = 1;
		int b = 1;
		if (a == b) {
			throw new Exception();
		}
	}

	@Test(expectException = Exception.class)
	public static void testWrong() throws Exception {
		int a = 1;
		int b = 1;
		if (a == b) {
			throw new Exception();
		}
	}

	@Test(expectException = Exception.class)
	public static void testWrongParam(int ab) throws Exception {
		int a = 1;
		int b = 1;
		if (a == b) {
			throw new Exception();
		}
	}
}
