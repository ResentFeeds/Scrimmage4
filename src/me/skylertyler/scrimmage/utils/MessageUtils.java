package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.config.types.Config;

import org.bukkit.Bukkit;
import static org.bukkit.ChatColor.*;

import org.bukkit.command.Command;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

public class MessageUtils {

	public static List<String> COMMAND_ERROR_MESSAGE = null;

	// broadcasting the starting match message!
	public static void broadcastStartMessage() {
		String header = DARK_PURPLE + " # # # # # # # # # # # # # # #";
		String match = DARK_PURPLE + "# #" + GOLD + " The match has started "
				+ DARK_PURPLE + "# #";
		String footer = DARK_PURPLE + " # # # # # # # # # # # # # # #";
		Bukkit.broadcastMessage(header);
		Bukkit.broadcastMessage(match);
		Bukkit.broadcastMessage(footer);
	}

	// broadcast the ending game message
	// TOdo add team winning!
	public static void broadcastFinishedMessage() {
		String header = DARK_PURPLE + " # # # # # # # # #";
		String match = DARK_PURPLE + "# #" + GOLD + " Game Over " + DARK_PURPLE
				+ "# #";
		String team = DARK_PURPLE + "# #" + RED + " No Team wins "
				+ DARK_PURPLE + "# #";
		String footer = DARK_PURPLE + " # # # # # # # # #";
		Bukkit.broadcastMessage(header);
		Bukkit.broadcastMessage(match);
		Bukkit.broadcastMessage(team);
		Bukkit.broadcastMessage(footer);
	}

	public static String notEnoughArgs(Command cmd) {
		String message = null;
		COMMAND_ERROR_MESSAGE = new ArrayList<>();
		COMMAND_ERROR_MESSAGE.add(RED + "Not enough arguments!");
		COMMAND_ERROR_MESSAGE.add(RED + " /" + cmd.getName() + " <"
				+ cmd.getUsage() + ">");

		for (String messages : COMMAND_ERROR_MESSAGE) {
			message = messages;
		}

		return message;
	}

	public static String authorList(Iterator<String> iterator) {
		String comma = Joiner.on(LIGHT_PURPLE + ", " + AQUA).join(iterator);
		return replaceLast(comma, ", ", " and ");
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

	public static String broadcast(String[] args) {

		StringBuilder msg = new StringBuilder();

		for (int i = 0; i < args.length; i++) {
			if (msg.length() > 0) {
				msg.append(" ");
			}

			msg.append(args[i]);
		}

		Config config = Scrimmage.getScrimmageInstance().getConfigFile();

		String format = null;
		String message = msg.toString();

		if (message.contains("`")) {
			format = BukkitUtils.colorize(message);
		} else {
			format = RED + message;
		}

		String result = format;
		return config.getBroadcastPrefix() + " " + result;
	}
}
