package com.pablojuice.framework.reports;

import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.drivers.WebDriverActions;
import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		Reporter.info("onTestStart");
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.info("onTestSuccess");
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.info("onTestFailure");
		ITestListener.super.onTestFailure(result);
		takeScreenshot();
		getSourceTree();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.info("onTestSkipped");
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Reporter.info("onTestFailedButWithinSuccessPercentage");
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		Reporter.info("onTestFailedWithTimeout");
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.info("onStart");
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.info("onFinish");
		ITestListener.super.onFinish(context);
	}

	private void takeScreenshot() {
		Allure.addAttachment(
				"Page screenshot",
				new ByteArrayInputStream(
						new WebDriverActions(DriverManager.getDriver()).takeScreenshotByte()
				)
		);
	}

	private void getSourceTree() {
		Allure.addAttachment("DOM Tree", DriverManager.getDriver().getPageSource());
	}

}
