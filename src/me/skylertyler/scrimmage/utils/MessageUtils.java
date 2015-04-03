package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import me.skylertyler.scrimmage.author.Author;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public class MessageUtils {

	public static List<String> COMMAND_ERROR_MESSAGE = null;

	// broadcasting the starting match message!
	public static void broadcastStartMessage() {
		ChatColor dark_purple = ChatColor.DARK_PURPLE;
		ChatColor green = ChatColor.GREEN;
		String header = dark_purple + " # # # # # # # # # # # # # # #";
		String match = dark_purple + "# #" + green + " The match has started "
				+ dark_purple + "# #";
		String footer = dark_purple + " # # # # # # # # # # # # # # #";
		Bukkit.broadcastMessage(header);
		Bukkit.broadcastMessage(match);
		Bukkit.broadcastMessage(footer);
	}

	// broadcast the ending game message
	// TOdo add team winning!
	public static void broadcastFinishedMessage() {
		ChatColor dark_purple = ChatColor.DARK_PURPLE;
		ChatColor gold = ChatColor.GOLD;
		ChatColor red = ChatColor.RED;
		String header = dark_purple + " # # # # # # # # #";
		String match = dark_purple + "# #" + gold + " Game Over " + dark_purple
				+ "# #";
		String team = dark_purple + "# #" + red + " No Team wins "
				+ dark_purple + "# #";
		String footer = dark_purple + " # # # # # # # # #";
		Bukkit.broadcastMessage(header);
		Bukkit.broadcastMessage(match);
		Bukkit.broadcastMessage(team);
		Bukkit.broadcastMessage(footer);
	}

	public static String notEnoughArgs(Command cmd) {
		String message = null;
		COMMAND_ERROR_MESSAGE = new ArrayList<>();
		COMMAND_ERROR_MESSAGE.add(ChatColor.RED + "Not enough arguments!");
		COMMAND_ERROR_MESSAGE.add(ChatColor.RED + " /" + cmd.getName() + " <"
				+ cmd.getUsage() + ">");

		for (String messages : COMMAND_ERROR_MESSAGE) {
			message = messages;
		}

		return message;
	}

	public static String authorList(Iterator<String> listIterator) {
		String comma = Joiner
				.on(ChatColor.LIGHT_PURPLE + ", " + ChatColor.AQUA).join(listIterator);
		return replaceLastString(comma, ", ", " and ");
	}

	public static String replaceLast(String string, String toReplace,
			String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos)
					+ replacement
					+ string.substring(pos + toReplace.length(),
							string.length());
		}
		return string;
	}

	public static String replaceLastString(String string, String toReplace,
			String replacment) {
		Preconditions.checkNotNull(toReplace, "toReplace");
		if (string != null && toReplace != null) {
			int pos = string.lastIndexOf(toReplace);
			if (pos > -1) {
				return string.substring(0, pos)
						+ replacment
						+ string.substring(pos + toReplace.length(),
								string.length());
			}
		}

		return string;
	}
}
