package com.pablojuice.framework.ui.design_patterns.pages;

import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class DesignPattern5 extends BaseDesignPattern {
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/article[1]/h1[1]")
	public TextElement logoLabel;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/nav[1]/div[1]/a[1]")
	public LinkElement navigateNextButton;

	public DesignPattern5(WebDriver driver) {
		super(Constants.LOGO_5, driver);
	}

	public static BaseDesignPattern initialize(WebDriver webDriver) {
		DesignPattern5 page = WrappedPageFactory.initElements(webDriver, DesignPattern5.class);
		page.setLogoLabel(page.logoLabel);
		page.setNavigateNextButton(page.navigateNextButton);
		return page;
	}
}
