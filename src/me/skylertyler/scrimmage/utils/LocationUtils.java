package me.skylertyler.scrimmage.utils;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.InfoModule;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocationUtils {
	public static Location parseLocationFromString(String loc) {
		Location location = null;
		String[] arr = loc.split(",");
		if (arr.length == 3) {
			int x = NumberUtils.parseInteger(arr[0]);
			int y = NumberUtils.parseInteger(arr[1]);
			int z = NumberUtils.parseInteger(arr[2]);
			location = new Location(Bukkit.getWorld("match-"
					+ ((InfoModule) Scrimmage.getScrimmageInstance()
							.getLoader().getContainer()
							.getModule(InfoModule.class)).getInfo().getName()),
					x, y, z);
		}
		return location;
	}

	public static Vector vectorFromString(String loc) {
		Location location = parseLocationFromString(loc);
		return location.toVector();
	}

}