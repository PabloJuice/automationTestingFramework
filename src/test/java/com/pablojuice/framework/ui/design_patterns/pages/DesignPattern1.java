package com.pablojuice.framework.ui.design_patterns.pages;

import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DesignPattern1 extends BaseDesignPattern {
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/h1[1]/span[2]")
	public TextElement logoLabel;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[3]/span[1]/a[1]")
	public LinkElement navigateNextButton;

	public DesignPattern1(WebDriver driver) {
		super(Constants.LOGO_1, driver);
	}

	public static BaseDesignPattern initialize(WebDriver webDriver) {
		DesignPattern1 page = WrappedPageFactory.initElements(webDriver, DesignPattern1.class);
		page.setLogoLabel(page.logoLabel);
		page.setNavigateNextButton(page.navigateNextButton);
		Assert.fail();
		return page;
	}
}
