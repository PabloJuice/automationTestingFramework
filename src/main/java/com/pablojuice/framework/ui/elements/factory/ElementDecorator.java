package com.pablojuice.framework.ui.elements.factory;

import com.pablojuice.framework.exceptions.UIException;
import com.pablojuice.framework.ui.elements.AbstractElement;
import com.pablojuice.framework.ui.elements.BaseElement;
import com.pablojuice.framework.ui.elements.Element;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ElementDecorator implements FieldDecorator {
	protected ElementLocatorFactory factory;

	public ElementDecorator(ElementLocatorFactory factory) {
		this.factory = factory;
	}

	@Override
	public Object decorate(ClassLoader loader, Field field) {
		if (!(Element.class.isAssignableFrom(field.getType()) || isDecoratableList(field))) {
			return null;
		}
		ElementLocator locator = factory.createLocator(field);
		if (locator == null || loader == null) {
			return null;
		}
		Class<?> fieldType = field.getType();
		if (WebElement.class.equals(fieldType)) {
			fieldType = BaseElement.class;
		}
		if (AbstractElement.class.isAssignableFrom(fieldType)) {
			return createWrappedElement(fieldType, locator.findElement());
		} else if (List.class.isAssignableFrom(fieldType)) {
			return createListWithWrappedElements(getErasureClass(field), locator.findElements());
		} else {
			return null;
		}
	}

	private Class getErasureClass(Field field) {
		Type genericType = field.getGenericType();
		if (!(genericType instanceof ParameterizedType)) {
			return null;
		}
		return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
	}

	private boolean isDecoratableList(Field field) {
		if (!List.class.isAssignableFrom(field.getType())) {
			return false;
		}
		Class erasureClass = getErasureClass(field);
		if (erasureClass == null || !WebElement.class.isAssignableFrom(erasureClass)) {
			return false;
		}
		return field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null;
	}

	protected <T> T createWrappedElement(Class<T> clazz, WebElement element) {
		try {
			return clazz.cast(
					clazz.getConstructor(WebElement.class)
							.newInstance(element)
			);
		} catch (Exception e) {
			throw new UIException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> createListWithWrappedElements(Class<T> clazz, List<WebElement> elements) {
		try {
			List<Object> wrappedList = new ArrayList<Object>();
			for (WebElement element : elements) {
				wrappedList.add(createWrappedElement(clazz, element));
			}
			return (List<T>) wrappedList;
		} catch (Exception e) {
			throw new UIException(e);
		}
	}
}
