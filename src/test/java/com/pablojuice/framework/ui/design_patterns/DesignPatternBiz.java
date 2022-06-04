package com.pablojuice.framework.ui.design_patterns;

import com.pablojuice.framework.base.BaseBiz;
import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.ui.design_patterns.pages.*;
import com.pablojuice.framework.util.Waiter;

import java.util.ArrayList;
import java.util.List;

public class DesignPatternBiz extends BaseBiz {
	private final List<BaseDesignPattern> pageList = new ArrayList<>();

	public DesignPatternBiz checkCurrentPage() {
		if (pageList.size() > 0) {
			BaseDesignPattern page = pageList.get(pageList.size() - 1);
			if (page != null) {
				page.checkPageLogo();
			}
		}
		return this;
	}

	public DesignPatternBiz navigateNextPage() {
		if (pageList.size() > 0) {
			BaseDesignPattern page = pageList.get(pageList.size() - 1);
			if (page != null) {
				page.navigateNext();
				Waiter.waitFor(Waiter.LOADING_MS);
			}
		}
		return this;
	}

	public DesignPatternBiz openFirstPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns");
		pageList.add(DesignPattern1.initialize(DriverManager.getDriver()));
		return this;
	}

	public DesignPatternBiz openSecondPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns/what-is-pattern");
		pageList.add(DesignPattern2.initialize(DriverManager.getDriver()));
		return this;
	}

	public DesignPatternBiz openThirdPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns/history");
		pageList.add(DesignPattern3.initialize(DriverManager.getDriver()));
		return this;
	}

	public DesignPatternBiz openFourthPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns/why-learn-patterns");
		pageList.add(DesignPattern4.initialize(DriverManager.getDriver()));
		return this;
	}

	public DesignPatternBiz openFifthPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns/criticism");
		pageList.add(DesignPattern5.initialize(DriverManager.getDriver()));
		return this;
	}

	public DesignPatternBiz openSixthPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns/classification");
		pageList.add(DesignPattern6.initialize(DriverManager.getDriver()));
		return this;
	}

	public DesignPatternBiz openSeventhPage() {
		DriverManager.getDriver().get("https://refactoring.guru/design-patterns/catalog");
		pageList.add(DesignPattern7.initialize(DriverManager.getDriver()));
		return this;
	}
}
