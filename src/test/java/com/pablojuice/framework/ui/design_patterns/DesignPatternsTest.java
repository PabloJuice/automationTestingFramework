package com.pablojuice.framework.ui.design_patterns;

import com.pablojuice.framework.base.BaseUITest;
import org.testng.annotations.Test;

@Test
public class DesignPatternsTest extends BaseUITest {

	/*
	 * TEST CASE No5
	 * Design patterns page navigation test
	 * */
	@Override
	public void test() {
		DesignPatternBiz designPatternBiz = new DesignPatternBiz();
		designPatternBiz.openFirstPage().checkCurrentPage().navigateNextPage();
		designPatternBiz.openSecondPage().checkCurrentPage().navigateNextPage();
		designPatternBiz.openThirdPage().checkCurrentPage().navigateNextPage();
		designPatternBiz.openFourthPage().checkCurrentPage().navigateNextPage();
		designPatternBiz.openFifthPage().checkCurrentPage().navigateNextPage();
		designPatternBiz.openSixthPage().checkCurrentPage().navigateNextPage();
		designPatternBiz.openSeventhPage().checkCurrentPage().navigateNextPage();
	}
}
