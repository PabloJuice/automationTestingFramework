package com.pablojuice.framework.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class DriverManager {
	private static final String DEFAULT_DRIVER_MANAGER_NAME = "Chrome";

	private static ThreadLocal<WebDriver> webDriver;

	private DriverManager() {
	}

	public static void setup(Class<? extends WebDriver> impl) {
		WebDriverManager.getInstance(getManagerForDriverByName(impl)).setup();
		WebDriver tempDriver;
		try {
			tempDriver = impl.getDeclaredConstructor().newInstance();
		} catch (NoSuchMethodException | InvocationTargetException |
				InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			tempDriver = new ChromeDriver();
		}
		if (webDriver == null) {
			webDriver = createThreadLocalDriver(tempDriver);
		} else {
			webDriver.set(tempDriver);
		}
	}

	public static WebDriverActions actions() {
		return new WebDriverActions(getDriver());
	}

	public static WebDriver getDriver() {
		return webDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	public static void closeDriver() {
		webDriver.remove();
	}

	private static ThreadLocal<WebDriver> createThreadLocalDriver(WebDriver driver) {
		return new ThreadLocal<>() {
			@Override
			protected WebDriver initialValue() {
				return driver;
			}

			@Override
			public void set(WebDriver newDriver) {
				closeDriverIfNotNull(get());
				super.set(newDriver);
			}

			@Override
			public void remove() {
				closeDriverIfNotNull(get());
				super.remove();
			}

			private void closeDriverIfNotNull(WebDriver webDriver) {
				if (webDriver != null) {
					webDriver.close();
					webDriver.quit();
				}
			}
		};
	}

	private static String getManagerForDriverByName(Class<? extends WebDriver> driverClass) {
		if (RemoteWebDriver.class.isAssignableFrom(driverClass)) {
			return driverClass.getSimpleName()
					.replace("Driver", "")
					.toLowerCase(Locale.ROOT)
					.trim();
		} else {
			return DEFAULT_DRIVER_MANAGER_NAME;
		}
	}
}
