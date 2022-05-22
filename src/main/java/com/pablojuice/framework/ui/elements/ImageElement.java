package com.pablojuice.framework.ui.elements;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


@Slf4j
public class ImageElement extends BaseElement {

	public ImageElement(By by) {
		super(by);
	}

	public ImageElement(WebElement webElement) {
		super(webElement);
	}

	public String getImageSource() {
		if (StringUtils.isNotBlank(getAttribute("src"))) {
			return getAttribute("src");
		} else {
			return getCssValue("background-image");
		}
	}
}