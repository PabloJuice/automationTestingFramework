package com.pablojuice.framework.ui.elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Slf4j
public class BaseElement extends AbstractElement {

	public BaseElement(By by) {
		super(by);
	}

	public BaseElement(WebElement webElement) {
		super(webElement);
	}

	@Override
	public boolean isReady() {
		return isEnabled() && isDisplayed();
	}

	@Override
	protected void callPreActions() {
		waitUntilDisplayed();
		scrollNoReport();
	}
}