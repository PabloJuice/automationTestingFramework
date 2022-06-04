package com.pablojuice.framework.ui.refactoring.pages;

import com.pablojuice.framework.base.BasePage;
import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public abstract class BaseRefactoringPage extends BasePage {

	private TextElement logoLabelElement;
	private LinkElement navigateNextButtonElement;
	private final String requiredLogo;

	public BaseRefactoringPage(String requiredLogo, WebDriver driver) {
		super(driver);
		this.requiredLogo = requiredLogo;
	}

	public void checkPageLogo() {
		Assert.assertNotNull(logoLabelElement, this.getClass().getSimpleName() + ": Logo is null");
		Assert.assertTrue(logoLabelElement.isDisplayed(), this.getClass().getSimpleName() + ": Logo is not displayed");
		Assert.assertEquals(requiredLogo,
							logoLabelElement.getText(),
							this.getClass().getSimpleName() + ": Logo is not valid");
	}

	public void navigateNext() {
		if (navigateNextButtonElement != null) {
			Assert.assertTrue(navigateNextButtonElement.isDisplayed(),
							  this.getClass().getSimpleName() + ": Button is not displayed");
			navigateNextButtonElement.click();
		}
	}


	public void setLogoLabel(TextElement logoLabel) {
		this.logoLabelElement = logoLabel;
	}

	public void setNavigateNextButton(LinkElement navigateNextButton) {
		this.navigateNextButtonElement = navigateNextButton;
	}

	protected static class Constants {
		public static final String LOGO_1 = "Refactoring";
		public static final String LOGO_2 = "Clean code";
		public static final String LOGO_3 = "Technical debt";
		public static final String LOGO_4 = "When to refactor";
		public static final String LOGO_5 = "How to refactor";
		public static final String LOGO_6 = "Code Smells";
	}
}
