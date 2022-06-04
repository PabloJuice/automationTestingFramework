package com.pablojuice.framework.ui.refactoring.pages;

import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RefactoringPage5 extends BaseRefactoringPage {
	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/article[1]/h1[1]")
	public TextElement logoLabel;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/article[1]/h1[1]")
	public LinkElement navigateNextButton;

	public RefactoringPage5(WebDriver driver) {
		super(Constants.LOGO_5, driver);
	}

	public static BaseRefactoringPage initialize(WebDriver webDriver) {
		RefactoringPage5 page = WrappedPageFactory.initElements(webDriver, RefactoringPage5.class);
		page.setLogoLabel(page.logoLabel);
		page.setNavigateNextButton(page.navigateNextButton);
		return page;
	}
}
