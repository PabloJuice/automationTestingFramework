package com.pablojuice.framework.ui.elements.factory;

import com.pablojuice.framework.exceptions.UIException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.lang.reflect.InvocationTargetException;


public class WrappedPageFactory extends PageFactory {

	public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy) {
		try {
			T page = pageClassToProxy.getConstructor(WebDriver.class).newInstance(driver);
			PageFactory.initElements(
					new ElementDecorator(new DefaultElementLocatorFactory(driver)), page);
			return page;
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException | NoSuchMethodException e) {
			throw new UIException(e);
		}
	}
}
