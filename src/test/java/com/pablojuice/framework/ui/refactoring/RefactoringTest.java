package com.pablojuice.framework.ui.refactoring;

import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.ui.base.BaseUITest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class RefactoringTest extends BaseUITest {

	public void test() throws InterruptedException {
		DriverManager.getDriver().get("https://refactoring.guru/");
		//Thread.sleep(3000);
		RefactoringPage page = RefactoringPage.initialize(DriverManager.getDriver());
		Assert.assertEquals(page.element.isDisplayed(), true);
	}
}
