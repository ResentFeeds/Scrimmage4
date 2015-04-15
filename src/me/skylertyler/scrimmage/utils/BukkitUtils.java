package me.skylertyler.scrimmage.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class BukkitUtils {

	/** Makes strings have pretty colors */
	public static String colorize(String s) {
		return ChatColor.translateAlternateColorCodes(charCharacter(s),
				ChatColor.translateAlternateColorCodes(charCharacter(s), s));
	}

	public static char charCharacter(String s) {
		return s.contains("`") ? '`' : '§';
	}

	public static List<String> colorizeList(List<String> list) {
		List<String> result = new ArrayList<String>();

		for (String line : list) {
			result.add(colorize(line));
		}

		return result;
	}
}
