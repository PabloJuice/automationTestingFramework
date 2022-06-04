package com.pablojuice.framework.ui.design_patterns.pages;

import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DesignPattern2 extends BaseDesignPattern {
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/article[1]/h1[1]")
	public TextElement logoLabel;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/nav[1]/div[1]/a[1]")
	public LinkElement navigateNextButton;

	public DesignPattern2(WebDriver driver) {
		super(Constants.LOGO_2, driver);
	}

	public static BaseDesignPattern initialize(WebDriver webDriver) {
		DesignPattern2 page = WrappedPageFactory.initElements(webDriver, DesignPattern2.class);
		page.setLogoLabel(page.logoLabel);
		page.setNavigateNextButton(page.navigateNextButton);
		return page;
	}
}
