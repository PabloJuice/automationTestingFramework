package com.pablojuice.framework.ui.elements;

import com.pablojuice.framework.config.FrameworkConfig;
import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.drivers.WebDriverActions;
import com.pablojuice.framework.reports.Reporter;
import com.pablojuice.framework.util.Waiter;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

abstract class AbstractElement implements Element {

	@Getter
	private final WebElement webElement;
	private static final int LOCATE_RETRIES = 2;
	private By by;

	protected AbstractElement(WebElement webElement) {
		this.webElement = webElement;
	}

	protected AbstractElement(By by) {
		this.by = by;
		callWait(Waiter.DISPLAY_WAIT_S,
				 () -> DriverManager.getDriver().findElement(by).isDisplayed(),
				 "element is displayed");
		webElement = DriverManager.getDriver().findElement(by);
	}

	protected void callWait(int timeout, Callable<Boolean> condition, String shortConditionText) {
		final Pair<Boolean, Integer> result = Waiter.wait(timeout, condition);
		final String MESSAGE =
				"Waiting for condition : " + shortConditionText + ". Timeout : " + timeout + " sec" + ". Time passed : " + result.getValue() + " ms.";
		if (result.getKey()) {
			if (FrameworkConfig.WAIT_REPORTING) {
				Reporter.info(MESSAGE + " - SUCCESS", toString());
			}
		} else {
			Reporter.error(MESSAGE + " - FAIL", toString());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("]").
				append(" - ");

		if (getWebElement() != null) {
			sb.append(getWebElement().toString());
		} else {
			sb.append(by.toString());
		}
		return sb.toString();
	}

	@Override
	public Element waitUntilDisplayed() {
		waitUntilDisplayed(SECONDS, Waiter.DISPLAY_WAIT_S);
		return this;
	}

	@Override
	public boolean waitUntilNotDisplayed() {
		return waitUntilNotDisplayed(Waiter.DISPLAY_WAIT_S);
	}

	@Override
	public boolean waitUntilNotDisplayed(final int waitTime) {
		callWait(waitTime, () -> !isDisplayed(), "element hidden");
		return !isDisplayed();
	}

	@Override
	public boolean waitUntilDisplayed(TimeUnit timeUnit, final int waitTime) {
		callWait(waitTime, this::isDisplayed, "element is displayed");
		return isDisplayed();
	}

	@Override
	public boolean waitUntilEnabled() {
		return waitUntilEnabled(Waiter.DISPLAY_WAIT_S);
	}

	@Override
	public boolean waitUntilNotEnabled() {
		return waitUntilNotDisplayed(Waiter.DISPLAY_WAIT_S);

	}

	@Override
	public boolean waitUntilEnabled(final int timeout) {
		callWait(timeout, () -> isDisplayed() && isEnabled(), "element is visible and active");
		return isDisplayed() && isEnabled();
	}

	@Override
	public boolean waitUntilNotEnabled(final int timeout) {
		callWait(timeout, () -> !isDisplayed() || !isEnabled(), "element is hidden or NOT active");
		return !isDisplayed() || !isEnabled();
	}

	@Override
	public boolean waitUntilReady() {
		return waitUntilReady(Waiter.DISPLAY_WAIT_S);
	}

	@Override
	public boolean waitUntilReady(final int timeout) {
		callWait(timeout, this::isReady, "The element is ready for action");
		return isReady();
	}

	@Override
	public Element click() {
		return callAction(webElement::click, "Click");
	}

	@Override
	public Element doubleClick() {
		return callAction(() -> new WebDriverActions(DriverManager.getDriver()).doubleClick(webElement),
						  "Double Click");
	}

	@Override
	public String getTagName() {
		return callAction(webElement::getTagName, "Read tag name", true, false, false);
	}

	@Override
	public String getAttribute(final String name) {
		return callAction(() -> webElement.getAttribute(name), "Read Attribute " + name, true, false, false);
	}

	@Override
	public String getCssValue(final String name) {
		return callAction(() -> webElement.getCssValue(name), format("Read value %s of CSS", name), true, false, false);
	}

	@Override
	public String getText() {
		return callAction(() -> webElement.getText().replaceAll("\u00A0", " "), "Getting the text of element");
	}

	@Override
	public boolean isDisplayed() {
		try {
			return callAction(webElement::isDisplayed,
							  "Check the visibility of the element",
							  false,
							  false,
							  false);
		} catch (Exception skipEx) {
			return false;
		}
	}

	@Override
	public boolean isEnabled() {
		try {
			return callAction(webElement::isEnabled, "Check element is Enabled", false, false, false);
		} catch (Exception skipEx) {
			return false;
		}
	}

	@Override
	public boolean isSelected() {
		try {
			return callAction(webElement::isSelected, "Check element is Selected", false, false, false);
		} catch (Exception skipEx) {
			return false;
		}
	}

	@Override
	public Element focus() {
		return callAction(() -> new WebDriverActions(DriverManager.getDriver()).moveToElement(webElement),
						  "Focus on the element. The element will be at the bottom.",
						  true,
						  true,
						  false);
	}

	@Override
	public Element scroll() {
		return callAction(() -> {
			new WebDriverActions(DriverManager.getDriver())
					.executeScript("arguments[0].scrollIntoView(true);",
								   getWebElement());
		}, "The top scroll to the element. The element will be at the top.", true, true, false);
	}

	@Override
	public Element blur() {
		return callAction(() -> {
			new WebDriverActions(DriverManager.getDriver())
					.executeScript("if ('createEvent' in document) {\n"
										   + "    var evt = document.createEvent('HTMLEvents');\n"
										   + "    evt.initEvent('blur', false, true)\n"
										   + "    arguments[0].dispatchEvent(evt);\n"
										   + "} else arguments[0].fireEvent('onblur');"
							, getWebElement());
		}, "Remove focus from the element", true, true, false);

	}

	@Override
	public boolean isFocused() {
		return callAction(() -> DriverManager.getDriver().switchTo().activeElement().equals(webElement),
						  "Checking the focus on the element",
						  true,
						  false,
						  false);
	}

	@Override
	public Element sendKeys(Keys... keys) {
		return callAction(() -> webElement.sendKeys(keys), "Keyboard input : " + Arrays.toString(keys));
	}

	@Override
	public Element sendKeys(String... keys) {
		return callAction(() -> webElement.sendKeys(keys), "Keyboard input : " + Arrays.toString(keys));
	}

	@Override
	public WebElement getWebElement() {
		return webElement;
	}

	@Override
	public Element getParentElement() {
		return callAction(() -> new BaseElement(webElement.findElement(By.xpath("./.."))),
						  "Keyboard input : ",
						  false,
						  true,
						  true);

	}

	protected void focusNoReport() {
		callAction(() -> new WebDriverActions(DriverManager.getDriver()).doubleClick(webElement),
				   "Focus on the element. The item will be at the bottom.",
				   false,
				   true,
				   false);
	}

	protected void scrollNoReport() {
		callAction(() -> {
			new WebDriverActions(DriverManager.getDriver())
					.executeScript("arguments[0].scrollIntoView(true);",
								   getWebElement());
		}, "The top scroll to the element. The element will be at the top.", false, true, false);
	}

	protected void reportAction(String message) {
		final String simpleMessage = "Action on the element: [" + getClass().getSimpleName() + "]";
		Reporter.info(simpleMessage + message, message + this);
	}

	protected <T> T callAction(Runnable runnable, String message) {
		return callAction(runnable, message, true, true, true);
	}

	protected <T extends Element> T callAction(Runnable runnable,
											   String message,
											   boolean isReporting,
											   boolean readyCheck,
											   boolean preActions) {
		return callAction(() -> {
			runnable.run();
			return (T) this;
		}, message, isReporting, readyCheck, preActions);
	}

	protected <T> T callAction(Supplier<T> supplier, String message) {
		if (message != null && message.isEmpty()) {
			return callAction(supplier, "", false, true, true);
		} else {
			return callAction(supplier, message, true, true, true);
		}
	}

	protected <T> T callAction(Supplier<T> supplier,
							   String message,
							   boolean isReporting,
							   boolean readyCheck,
							   boolean preActions) {
		Exception ex = null;
		if (readyCheck) {
			waitUntilReady();
		}
		if (preActions) {
			callPreActions();
		}
		T result = null;
		int retries = 0;
		long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(Waiter.INTERACT_WAIT_S);
		while (Instant.now().toEpochMilli() < expireTime && retries < LOCATE_RETRIES) {
			try {
				result = supplier.get();
				return result;
			} catch (Exception e) {
				ex = e;
				retries++;
			} finally {
				if (isReporting && FrameworkConfig.ELEMENT_REPORTING) {
					reportAction(message + System.lineSeparator() + "Result : " + Objects.requireNonNullElse(result,
																											 "empty") + System.lineSeparator());
				}

			}
		}
		throw exception(ex);
	}

	protected abstract void callPreActions();

	private NoSuchElementException exception(Exception ex) {
		return new NoSuchElementException("The base element could not be found : " + this, ex);
	}

	@Override
	public BaseElement createBaseElement(By by) {
		return new BaseElement(getWebElement().findElement(by));
	}

	@Override
	public List<BaseElement> createBaseElements(By by) {
		return getWebElement().findElements(by).stream().map(BaseElement::new).collect(Collectors.toList());

	}

	@Override
	public ImageElement createImageElement(By by) {
		return new ImageElement(getWebElement().findElement(by));

	}

	@Override
	public List<ImageElement> createImageElements(By by) {
		return getWebElement().findElements(by).stream().map(ImageElement::new).collect(Collectors.toList());
	}

	@Override
	public LinkElement createLinkElement(By by) {
		return new LinkElement(getWebElement().findElement(by));
	}

	@Override
	public List<LinkElement> createLinkElements(By by) {
		return getWebElement().findElements(by).stream().map(LinkElement::new).collect(Collectors.toList());
	}

	@Override
	public TextInputElement createTextInputElement(By by) {
		return new TextInputElement(getWebElement().findElement(by));
	}

	@Override
	public List<TextInputElement> createTextInputElements(By by) {
		return getWebElement().findElements(by).stream().map(TextInputElement::new).collect(Collectors.toList());

	}

	@Override
	public SelectListElement createSelectListElement(By by) {
		return new SelectListElement(getWebElement().findElement(by));
	}

	@Override
	public List<SelectListElement> createSelectListElements(By by) {
		return getWebElement().findElements(by).stream().map(SelectListElement::new).collect(Collectors.toList());

	}

	@Override
	public FileUploadElement createFileUploadElement(By by) {
		return new FileUploadElement(getWebElement().findElement(by));
	}

	@Override
	public TableElement createTableElement(By by) {
		return new TableElement(getWebElement().findElement(by));
	}

	@Override
	public List<TableElement> createTableElements(By by) {
		return getWebElement().findElements(by).stream().map(TableElement::new).collect(Collectors.toList());

	}

	@Override
	public TableRowElement createTableRowElement(By by) {
		return new TableRowElement(getWebElement().findElement(by));
	}

	@Override
	public List<TableRowElement> createTableRowElements(By by) {
		return getWebElement().findElements(by).stream().map(TableRowElement::new).collect(Collectors.toList());

	}

	@Override
	public ButtonElement createButtonElement(By by) {
		return new ButtonElement(getWebElement().findElement(by));
	}

	@Override
	public List<ButtonElement> createButtonElements(By by) {
		return getWebElement().findElements(by).stream().map(ButtonElement::new).collect(Collectors.toList());
	}

	@Override
	public CheckboxElement createCheckboxElement(By by) {
		return new CheckboxElement(getWebElement().findElement(by));
	}

	@Override
	public List<CheckboxElement> createCheckboxElements(By by) {
		return getWebElement().findElements(by).stream().map(CheckboxElement::new).collect(Collectors.toList());
	}
}
