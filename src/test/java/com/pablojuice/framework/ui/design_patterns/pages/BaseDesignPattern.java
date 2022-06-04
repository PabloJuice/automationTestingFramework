package com.pablojuice.framework.ui.design_patterns.pages;

import com.pablojuice.framework.base.BasePage;
import com.pablojuice.framework.ui.elements.LinkElement;
import com.pablojuice.framework.ui.elements.TextElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public abstract class BaseDesignPattern extends BasePage {

	private TextElement logoLabelElement;
	private LinkElement navigateNextButtonElement;
	private final String requiredLogo;

	public BaseDesignPattern(String requiredLogo, WebDriver driver) {
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
		public static final String LOGO_1 = "PATTERNS";
		public static final String LOGO_2 = "What's a design pattern?";
		public static final String LOGO_3 = "History of patterns";
		public static final String LOGO_4 = "Why should I learn patterns?";
		public static final String LOGO_5 = "Criticism of patterns";
		public static final String LOGO_6 = "Classification of patterns";
		public static final String LOGO_7 = "The Catalog of Design Patterns";
	}
}
