package com.pablojuice.framework.util;

import com.pablojuice.framework.config.FrameworkConfig;
import com.pablojuice.framework.reports.Reporter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.Callable;


public class Waiter {
	public static final int INTERACT_WAIT_S = FrameworkConfig.ACTION_TIMEOUT;
	public static final int DISPLAY_WAIT_S = FrameworkConfig.WAIT_TIMEOUT;
	public static final int INTERVAL_MS = FrameworkConfig.INTERVAL_MS;
	public static final int LOADING_MS = FrameworkConfig.LOADING_MS;

	private Waiter() {

	}

	public static Pair<Boolean, Long> waitUntil(int maxTimeout, Callable<Boolean> callable) {
		final long startTime = System.currentTimeMillis();
		boolean result = true;
		try {
			while (!callable.call() && maxTimeout > System.currentTimeMillis() - startTime) {
				try {
					Thread.sleep(INTERVAL_MS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ignored) {
			result = false;
		}
		return Pair.of(result, System.currentTimeMillis() - startTime);
	}

	public static void waitCheck(int timeout,
								 Callable<Boolean> condition,
								 String shortConditionText,
								 String fullDescription) {
		final Pair<Boolean, Long> result = Waiter.waitUntil(timeout, condition);
		final String MESSAGE =
				"Waiting for condition : " + shortConditionText + ". Timeout : " + timeout + " sec" + ". Time passed : " + result.getValue() + " ms.";
		if (result.getKey()) {
			if (FrameworkConfig.WAIT_REPORTING) {
				Reporter.info(MESSAGE + " - SUCCESS", fullDescription);
			}
		} else {
			Reporter.error(MESSAGE + " - FAIL", fullDescription);
		}
	}

	public static void waitFor(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}
}
