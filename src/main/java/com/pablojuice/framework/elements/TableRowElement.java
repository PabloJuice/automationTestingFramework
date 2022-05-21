package com.pablojuice.framework.elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


@Slf4j
public class TableRowElement extends BaseElement {
	private List<BaseElement> columns;

	public TableRowElement(By by) {
		super(by);
	}

	public TableRowElement(WebElement webElement) {
		super(webElement);
	}

	public BaseElement getColumn(int index) {
		return getColumns().get(index);
	}

	public List<BaseElement> getColumns() {
		if (columns == null) {
			columns = createBaseElements(By.tagName("td"));
		}
		return columns;
	}
}