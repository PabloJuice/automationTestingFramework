package com.pablojuice.framework.ui.elements;

import java.util.List;


public interface SelectList {

	void select(String optionText);

	void selectIfContains(String optionText);

	boolean isDisplayed();

	String getSelectedOption();

	List<String> getAvailableOptions();
}
