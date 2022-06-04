package com.pablojuice.framework.config;

import com.pablojuice.framework.util.Resource;


public class FrameworkConfig {

	private FrameworkConfig() {

	}

	private static final Resource resources = Resource.of("framework");

	public static final int WAIT_TIMEOUT = resources.getInt("elements.wait.timeout", 20);

	public static final int ACTION_TIMEOUT = resources.getInt("elements.action.timeout", 5);

	public static final int ACTIONS_TRY = resources.getInt("elements.actions.try", 2);

	public static final boolean ELEMENT_REPORTING = resources.getBoolean("elements.reporting", false);

	public static final boolean SCREENING = resources.getBoolean("reporting.screenshot", false);

	public static final boolean WAIT_REPORTING = resources.getBoolean("reporting.waits", false);

	public static final int INTERVAL_MS = resources.getInt("elements.wait.interval", 0);

	public static final int LOADING_MS = resources.getInt("elements.wait.loading", 1000);

	public static final boolean LOG_VIDEO = resources.getBoolean("reporting.video", false);
}
