package com.pablojuice.framework.base;

import com.pablojuice.framework.data.SpringConfiguration;
import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.reports.AllureListener;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(AllureListener.class)
@ContextConfiguration(classes = SpringConfiguration.class)
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
