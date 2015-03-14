package me.skylertyler.scrimmage.utils;

import org.bukkit.ChatColor;

public class BukkitUtils {

	/** Makes strings have pretty colors */
	public static String colorize(String s) {
		return ChatColor.translateAlternateColorCodes('`',
				ChatColor.translateAlternateColorCodes('`', s));
	}
}
