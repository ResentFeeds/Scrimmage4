package me.skylertyler.scrimmage.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ConsoleUtils {

	public ConsoleUtils() {

	}

	public static CommandSender sender = Bukkit.getConsoleSender();

	public static void sendConsoleMessage(String message) {
		sender.sendMessage(message);
	}
	
}
