package me.skylertyler.scrimmage.utils;

import me.skylertyler.scrimmage.Scrimmage;  

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class LocationUtils {
	public static Location parseLocationFromString(String loc) {
		String[] arr = loc.split(",");
		if (arr.length == 3) {
			int x = NumberUtils.parseInteger(arr[0]);
			int y = NumberUtils.parseInteger(arr[1]);
			int z = NumberUtils.parseInteger(arr[2]);
			Location location = new Location(Scrimmage.getScrimmageInstance()
					.getMatchHandler().getCurrentMatch().getWorld(), x, y, z);
			return location;
		}
		return null;
	}

	public static Vector vectorFromString(String loc) {
		Location location = parseLocationFromString(loc);
		return location.toVector();
	}
}