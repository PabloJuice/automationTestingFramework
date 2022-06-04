package com.pablojuice.framework.base;

import io.restassured.RestAssured;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;

public abstract class BaseAPITest extends AbstractTestNGSpringContextTests {
	@BeforeTest
	public void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	public abstract void test();
}
