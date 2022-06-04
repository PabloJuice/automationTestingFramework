package com.pablojuice.framework.base;

import com.pablojuice.framework.drivers.DriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseUITest extends AbstractTestNGSpringContextTests {

	@BeforeTest
	public void setup() {
		DriverManager.setup(ChromeDriver.class);
	}

	public abstract void test();

	@AfterTest
	public void release() {
		DriverManager.closeDriver();
	}
}
