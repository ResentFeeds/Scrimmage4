package me.skylertyler.scrimmage.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	public static Logger logger = Logger.getLogger("Minecraft");

	public static void logMessage(Level level, String message) {
		logger.log(level, message);
	}

	public static void logInfo(String message) {
		logMessage(Level.INFO, message);
	}

	public static void logSevere(String message) {
		logMessage(Level.SEVERE, message);
	}

	public static void logWarning(String message) {
		logMessage(Level.WARNING, message);
	}

	public static Logger getLogger() {
		return logger;
	}
}
