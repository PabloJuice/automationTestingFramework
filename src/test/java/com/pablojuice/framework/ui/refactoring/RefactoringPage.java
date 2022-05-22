package com.pablojuice.framework.ui.refactoring;

import com.pablojuice.framework.ui.base.BasePage;
import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.factory.WrappedPageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class RefactoringPage extends BasePage {

	@FindBy(xpath = "/html[1]/body[1]/div[1]/main[1]/div[1]/div[2]/h1[1]")
	public LinkElement element;

	public RefactoringPage(WebDriver driver) {
		super(driver);
	}

	public static RefactoringPage initialize(WebDriver webDriver) {
		return WrappedPageFactory.initElements(webDriver, RefactoringPage.class);
	}
}
