package com.pablojuice.framework.util;

import com.pablojuice.framework.reports.Reporter;
import lombok.Getter;
import lombok.Setter;

public class Variables {

	private Variables() {

	}

	@Setter
	@Getter
	public static final Reporter.Builder reporterBuilder = new Reporter.Builder();

	public static String getEnvironmentVariable(String name, String defaultValue) {
		String value = System.getenv(name.toUpperCase());

		if (value != null && !value.isEmpty()) {
			reporterBuilder.append(String.format("Get Env parameter %s; value = %s", name, value));
		} else {
			if (defaultValue != null) {
				reporterBuilder.append(String.format("Can not get Env parameter %s; default value = %s",
													 name,
													 defaultValue));
				value = defaultValue;
			} else {
				reporterBuilder.append(String.format("Can not get Env parameter %s; NO DEFAULT VALUE", name));
			}
		}
		return value;
	}

	public static String getEnvironmentVariable(String name) {
		return getEnvironmentVariable(name, null);
	}
}
