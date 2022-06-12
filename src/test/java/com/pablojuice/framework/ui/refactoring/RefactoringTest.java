package com.pablojuice.framework.ui.refactoring;

import com.pablojuice.framework.base.BaseUITest;
import com.pablojuice.framework.data.SpringConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(classes = SpringConfiguration.class)
public class RefactoringTest extends BaseUITest {

	/*
	 * TEST CASE No5
	 * Refactoring page navigation test
	 * */
	@Override
	public void test() {
		RefactoringBiz refactoringBiz = new RefactoringBiz();
		refactoringBiz.openFirstPage().checkCurrentPage().navigateNextPage();
		refactoringBiz.openSecondPage().checkCurrentPage().navigateNextPage();
		refactoringBiz.openThirdPage().checkCurrentPage().navigateNextPage();
		refactoringBiz.openFourthPage().checkCurrentPage().navigateNextPage();
		refactoringBiz.openFifthPage().checkCurrentPage().navigateNextPage();
		refactoringBiz.openSixthPage().checkCurrentPage().navigateNextPage();
	}
}
