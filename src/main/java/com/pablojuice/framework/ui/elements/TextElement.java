package com.pablojuice.framework.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextElement extends BaseElement {
	public TextElement(By by) {
		super(by);
	}

	public TextElement(WebElement webElement) {
		super(webElement);
	}
}
