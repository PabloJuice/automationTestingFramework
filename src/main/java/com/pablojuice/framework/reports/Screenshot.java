package com.pablojuice.framework.reports;

import com.pablojuice.framework.drivers.DriverManager;
import com.pablojuice.framework.drivers.WebDriverActions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.UnreachableBrowserException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;


@Slf4j
public class Screenshot implements ScreenShooter {

	private static final String SCREENSHOT_DIR = getDirectory("screenshots");
	private static final String HTML_SCREENSHOT_DIR = getDirectory("html-screenshots");

	@Override
	public byte[] takeBrowserScreenshot() {
		if (DriverManager.hasDriver()) {
			try {
				log.info("Creating Screenshots");
				return new WebDriverActions(DriverManager.getDriver()).takeScreenshotByte();
			} catch (UnreachableBrowserException e) {
				log.error(" Unable to take screenshot: " + e);
			}
		} else {
			log.warn("Could not make screenshot of Browser. Most likely it is not started");

		}
		return new byte[0];
	}

	@Override
	public byte[] takeDesktopScreenshot() {
		BufferedImage originalImage = getScreenAsBufferedImage();
		if (originalImage == null) {
			return new byte[0];
		}
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			ImageIO.write(originalImage, "png", outputStream);
			outputStream.flush();
			return outputStream.toByteArray();
		} catch (IOException e) {
			log.warn("Could not make a screenshot of the Desktop", e);
		}
		return new byte[0];
	}

	public static void takeScreenshot(String filename) {
		if (log.isDebugEnabled()) {
			takeFullDesktopScreenshot(filename);
		} else {
			if (DriverManager.hasDriver()) {
				try {
					filename += "_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + ".png";
					File scrFile = new WebDriverActions(DriverManager.getDriver()).takeScreenshot();
					log.info("Creating Screenshot: " + SCREENSHOT_DIR + filename);
					FileUtils.copyFile(scrFile, new File(SCREENSHOT_DIR + filename));
				} catch (IOException | UnreachableBrowserException e) {
					log.error(" Unable to take screenshot: " + e);
				}
			} else {
				log.error("Webdriver not started. Unable to take screenshot");
			}
		}
	}

	public static void takeFullDesktopScreenshot(String filename) {
		try {
			filename += "_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + ".png";
			BufferedImage img = new Screenshot().getScreenAsBufferedImage();
			File output = new File(filename);
			ImageIO.write(img, "png", output);
			log.info("Creating FULL SCREEN Screenshot: " + SCREENSHOT_DIR + filename);
			FileUtils.copyFile(output, new File(SCREENSHOT_DIR + filename));
			FileUtils.deleteQuietly(output);
		} catch (IOException e) {
			log.error(e.toString());
		}
	}

	public static void takeHTMLScreenshot(String filename) {
		if (!DriverManager.hasDriver()) {
			log.error("Webdriver not started. Unable to take html snapshot");
			return;
		}
		filename += "_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + ".html";
		Writer writer = null;
		log.info("Capturing HTML snapshot: " + HTML_SCREENSHOT_DIR + filename);
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(HTML_SCREENSHOT_DIR + filename), StandardCharsets.UTF_8));
			writer.write(new WebDriverActions(DriverManager.getDriver()).getHtml());
		} catch (IOException ex) {
			log.info("Unable to write out current state of html");
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				log.info("Unable to close writer");
			}
		}
	}

	private BufferedImage getScreenAsBufferedImage() {
		BufferedImage img = null;
		try {
			Robot r;
			r = new Robot();
			Toolkit t = Toolkit.getDefaultToolkit();
			Rectangle rect = new Rectangle(t.getScreenSize());
			img = r.createScreenCapture(rect);
		} catch (AWTException e) {
			log.error(e.toString());
		}
		return img;
	}

	private static String getDirectory(String name) {
		String screenshotDirectory = String.format("%s../%s/",
												   ClassLoader.getSystemClassLoader().getSystemResource("").getPath(),
												   name);
		File file = new File(screenshotDirectory);
		if (!file.exists()) file.mkdir();
		log.info("Creating screenshot directory: " + screenshotDirectory);
		return screenshotDirectory;
	}
}
