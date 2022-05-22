package com.pablojuice.framework.reports;


public interface ScreenShooter {

	byte[] takeBrowserScreenshot();

	byte[] takeDesktopScreenshot();
}
