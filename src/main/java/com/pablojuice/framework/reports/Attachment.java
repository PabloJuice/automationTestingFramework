package com.pablojuice.framework.reports;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
public class Attachment {

	private final ScreenShooter screenShooter;

	public Attachment() {
		screenShooter = new Screenshot();
	}

	public Attachment(ScreenShooter screenShooter) {

		this.screenShooter = screenShooter;
	}

	@io.qameta.allure.Attachment(value = "Screenshot : {0}", type = "image/png")
	protected byte[] attachScreen(String screenName) {
		if (screenShooter != null) {
			byte[] result = screenShooter.takeBrowserScreenshot();
			if (result == null || result.length == 0) {
				result = screenShooter.takeDesktopScreenshot();
			}
			return result;
		} else {
			return new byte[0];
		}
	}

	@io.qameta.allure.Attachment(value = "File : {0}")
	protected byte[] attachFile(String fileDesc, Path path) {
		byte[] result = new byte[0];
		try {
			result = Files.readAllBytes(path);
		} catch (IOException e) {
			log.warn("Error reading file for attachment: " + fileDesc, e);
		}
		return result;
	}

	@io.qameta.allure.Attachment(value = "File : {0}")
	protected byte[] attachFile(String fileName, byte[] file) {
		return file;
	}

	@io.qameta.allure.Attachment(value = "Text : {0}",
			type = "text/plain")
	protected String attachText(String title, String text) {
		return text;
	}

	@io.qameta.allure.Attachment(value = "Html : {0}",
			type = "text/html")
	protected String attachHtml(String title, String html) {
		return html;
	}
}
