package com.pablojuice.framework.ui.design_patterns.pages;

import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DesignPattern7 extends BaseDesignPattern {
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/h1[1]")
	public TextElement logoLabel;

	@FindBy(xpath = "/html/body/div[1]/main/div/div/div/div[4]/a[5]")
	public LinkElement navigateNextButton;

	public DesignPattern7(WebDriver driver) {
		super(Constants.LOGO_7, driver);
	}

	public static BaseDesignPattern initialize(WebDriver webDriver) {
		DesignPattern7 page = WrappedPageFactory.initElements(webDriver, DesignPattern7.class);
		page.setLogoLabel(page.logoLabel);
		page.setNavigateNextButton(null);
		return page;
	}
}
