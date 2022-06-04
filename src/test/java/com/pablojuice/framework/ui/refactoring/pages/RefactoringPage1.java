package com.pablojuice.framework.ui.refactoring.pages;

import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RefactoringPage1 extends BaseRefactoringPage {
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/h1[1]")
	public TextElement logoLabel;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[2]/span[1]/a[1]")
	public LinkElement navigateNextButton;

	public RefactoringPage1(WebDriver driver) {
		super(Constants.LOGO_1, driver);
	}

	public static BaseRefactoringPage initialize(WebDriver webDriver) {
		RefactoringPage1 page = WrappedPageFactory.initElements(webDriver, RefactoringPage1.class);
		page.setLogoLabel(page.logoLabel);
		page.setNavigateNextButton(page.navigateNextButton);
		return page;
	}
}
