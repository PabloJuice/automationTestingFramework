package com.pablojuice.framework.ui.elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


@Slf4j
public class ButtonElement extends BaseElement {

	public ButtonElement(By by) {
		super(by);
	}

	public ButtonElement(WebElement webElement) {
		super(webElement);
	}
}