package com.pablojuice.framework.elements;

import java.util.concurrent.TimeUnit;


public interface ElementWaiters {

	ElementWaiters waitUntilDisplayed();

	boolean waitUntilNotDisplayed();

	boolean waitUntilNotDisplayed(final int waitTime);

	boolean waitUntilDisplayed(final TimeUnit timeUnit, final int seconds);

	boolean waitUntilEnabled();

	boolean waitUntilNotEnabled();

	boolean waitUntilEnabled(final int time);

	boolean waitUntilNotEnabled(final int time);

	boolean waitUntilReady();

	boolean waitUntilReady(final int time);
}
