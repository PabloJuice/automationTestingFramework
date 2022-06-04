package com.pablojuice.framework.ui.refactoring;

import com.pablojuice.framework.base.BaseBiz;
import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.ui.refactoring.pages.*;
import com.pablojuice.framework.util.Waiter;

import java.util.ArrayList;
import java.util.List;

public class RefactoringBiz extends BaseBiz {
	private final List<BaseRefactoringPage> pageList = new ArrayList<>();

	public RefactoringBiz checkCurrentPage() {
		if (pageList.size() > 0) {
			BaseRefactoringPage page = pageList.get(pageList.size() - 1);
			if (page != null) {
				page.checkPageLogo();
			}
		}
		return this;
	}

	public RefactoringBiz navigateNextPage() {
		if (pageList.size() > 0) {
			BaseRefactoringPage page = pageList.get(pageList.size() - 1);
			if (page != null) {
				page.navigateNext();
				Waiter.waitFor(Waiter.LOADING_MS);
			}
		}
		return this;
	}

	public RefactoringBiz openFirstPage() {
		DriverManager.getDriver().get("https://refactoring.guru/refactoring");
		pageList.add(RefactoringPage1.initialize(DriverManager.getDriver()));
		return this;
	}

	public RefactoringBiz openSecondPage() {
		DriverManager.getDriver().get("https://refactoring.guru/refactoring/what-is-refactoring");
		pageList.add(RefactoringPage2.initialize(DriverManager.getDriver()));
		return this;
	}

	public RefactoringBiz openThirdPage() {
		DriverManager.getDriver().get("https://refactoring.guru/refactoring/technical-debt");
		pageList.add(RefactoringPage3.initialize(DriverManager.getDriver()));
		return this;
	}

	public RefactoringBiz openFourthPage() {
		DriverManager.getDriver().get("https://refactoring.guru/refactoring/when");
		pageList.add(RefactoringPage4.initialize(DriverManager.getDriver()));
		return this;
	}

	public RefactoringBiz openFifthPage() {
		DriverManager.getDriver().get("https://refactoring.guru/refactoring/how-to");
		pageList.add(RefactoringPage5.initialize(DriverManager.getDriver()));
		return this;
	}

	public RefactoringBiz openSixthPage() {
		DriverManager.getDriver().get("https://refactoring.guru/refactoring/smells");
		pageList.add(RefactoringPage6.initialize(DriverManager.getDriver()));
		return this;
	}
}
