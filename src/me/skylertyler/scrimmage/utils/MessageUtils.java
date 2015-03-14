package me.skylertyler.scrimmage.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageUtils {

	// broadcasting the starting match message!
	public static void broadcastStartMessage() {
		ChatColor dark_purple = ChatColor.DARK_PURPLE;
		ChatColor green = ChatColor.GREEN;
		String header = dark_purple +" # # # # # # # # # # # # # # #";
		String match = dark_purple + "# #" + green + " The match has started " + dark_purple + "# #";
		String footer = dark_purple +" # # # # # # # # # # # # # # #";
		Bukkit.broadcastMessage(header);
		Bukkit.broadcastMessage(match);
		Bukkit.broadcastMessage(footer);
	}
	
	
	//broadcast the ending game message
	//TOdo add team winning!
	public static void broadcastFinishedMessage() {
		ChatColor dark_purple = ChatColor.DARK_PURPLE;
		ChatColor gold = ChatColor.GOLD;
		ChatColor red = ChatColor.RED;
		String header = dark_purple +" # # # # # # # # #";
		String match = dark_purple + "# #" + gold +" Game Over " + dark_purple + "# #";
		String team = dark_purple + "# #" + red + " No Team wins " + dark_purple + "# #";
		String footer = dark_purple +" # # # # # # # # #";
		Bukkit.broadcastMessage(header);
		Bukkit.broadcastMessage(match);
		Bukkit.broadcastMessage(team);
		Bukkit.broadcastMessage(footer);
	}
}
