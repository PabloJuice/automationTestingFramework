package com.pablojuice.framework.util;


public class OSUtil {

	public enum OS {
		LINUX, MACOS, WIN, OTHER
	}

	public static OS getOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return OS.WIN;
		} else if (os.contains("nux") || os.contains("nix")) {
			return OS.LINUX;
		} else if (os.contains("mac")) {
			return OS.MACOS;
		} else {
			return OS.OTHER;
		}
	}
}
