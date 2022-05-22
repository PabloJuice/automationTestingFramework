package com.pablojuice.framework.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class TableElement extends BaseElement {
	private List<TableRowElement> rows;
	private TableRowElement header;

	public TableElement(WebElement avatar) {
		super(avatar);
	}

	public TableElement(By by) {
		super(by);
	}


	public int size() {
		return 1 + getRows().size();
	}

	public List<TableRowElement> getRows() {
		if (rows == null) {
			rows = createTableRowElements(By.tagName("tr"));
		}
		return rows;
	}

	public TableRowElement getHeader() {
		if (header == null) {
			header = getRows().get(0);
		}
		return header;
	}

	public TableRowElement getRow(int index) {
		return getRows().get(index);
	}
}