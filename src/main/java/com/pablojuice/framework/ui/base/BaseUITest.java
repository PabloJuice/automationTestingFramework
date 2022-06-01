package com.pablojuice.framework.ui.base;

import com.pablojuice.framework.drivers.DriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseUITest {

	@BeforeTest
	public void setup() {
		DriverManager.setup(ChromeDriver.class);
	}

	@AfterTest
	public void release() {
		DriverManager.closeDriver();
	}
}
