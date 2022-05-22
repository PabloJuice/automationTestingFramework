package com.pablojuice.framework.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class TextInputElement extends BaseElement {

	public TextInputElement(WebElement avatar) {
		super(avatar);
	}

	public TextInputElement(By by) {
		super(by);
	}

	public TextInputElement type(final String text) {
		if (text == null)
			return this;
		return callAction(() -> {
			getWebElement().clear();
			getWebElement().sendKeys(text);
			return this;
		}, "Text input : " + text);
	}

	public void clear() {
		getWebElement().clear();
	}

	public TextInputElement pressKey(final Keys key) {
		if (key == null) return this;
		sendKeys(key);
		return this;
	}

	public TextInputElement appendType(final String text) {
		return pressKey(text);
	}

	public TextInputElement pressKey(final String key) {
		if (key == null) return this;
		sendKeys(key);
		return this;
	}

	public TextInputElement prependType(final String text) {
		if (text == null) return this;
		pressKey(Keys.chord(Keys.COMMAND, Keys.ARROW_UP) + text);
		moveCursorToEndOfInput();
		return this;
	}

	private void moveCursorToEndOfInput() {
		getWebElement().sendKeys(Keys.chord(Keys.COMMAND, Keys.ARROW_DOWN));
		getWebElement().click();
	}
}