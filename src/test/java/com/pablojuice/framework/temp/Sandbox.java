package com.pablojuice.framework.temp;

import com.pablojuice.framework.drivers.DriverManager;
import org.openqa.selenium.edge.EdgeDriver;

public class Sandbox {
	public static void main(String[] args) throws InterruptedException {
		DriverManager.setup(EdgeDriver.class);
		DriverManager.getDriver().get("https://refactoring.guru/");
		Thread.sleep(5000);
		DriverManager.closeDriver();
	}
}
