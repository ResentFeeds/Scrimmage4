package me.skylertyler.scrimmage.utils;

import org.bukkit.ChatColor;

public class ColorUtils {

	public static ChatColor parseChatColor(String attribute) {
		String name = attribute.replace(" ", "_").toUpperCase();
		return ChatColor.valueOf(name);
	} 
}
