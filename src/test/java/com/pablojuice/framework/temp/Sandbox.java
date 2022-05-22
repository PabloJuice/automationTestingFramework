package com.pablojuice.framework.temp;

import com.pablojuice.framework.drivers.DriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sandbox {

	public static void main(String[] args) throws InterruptedException {
		DriverManager.setup(ChromeDriver.class);
		DriverManager.getDriver().get("https://refactoring.guru/");
		//ElementFactory.initElements(DriverManager.getDriver(), Sandbox.class);
		Thread.sleep(5000);
		DriverManager.closeDriver();
	}
}
