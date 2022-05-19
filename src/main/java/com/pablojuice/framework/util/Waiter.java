package com.pablojuice.framework.util;

import com.pablojuice.framework.config.InternalConfig;
import com.pablojuice.framework.reports.Reporter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class Waiter {
	public static final int INTERACT_WAIT_S = InternalConfig.ACTION_TIMEOUT;
	public static final int DISPLAY_WAIT_S = InternalConfig.WAIT_TIMEOUT;
	public static final int INTERVAL_MS = InternalConfig.INTERVAL_MS;

	public static Pair<Boolean, Integer> wait(int sec, Callable<Boolean> callable) {
		final List<Integer> time = new ArrayList<>();
		boolean result = true;

		try {
			while (!callable.call()) {
				try {
					Thread.sleep(INTERVAL_MS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception ignored) {
			result = false;
		}
		int resTime = sec * 1000;
		if (!time.isEmpty()) {
			resTime = time.get(time.size() - 1);
		}
		return Pair.of(result, resTime);
	}

	public static void waitCheck(int timeout,
								 Callable<Boolean> condition,
								 String shortConditionText,
								 String fullDescription) {
		final Pair<Boolean, Integer> result = Waiter.wait(timeout, condition);
		final String MESSAGE =
				"Waiting for condition : " + shortConditionText + ". Timeout : " + timeout + " sec" + ". Time passed : " + result.getValue() + " ms.";
		if (result.getKey()) {
			if (InternalConfig.WAIT_REPORTING) {
				Reporter.info(MESSAGE + " - SUCCESS", fullDescription);
			}
		} else {
			Reporter.error(MESSAGE + " - FAIL", fullDescription);
		}
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ignored) {
		}
	}
}
