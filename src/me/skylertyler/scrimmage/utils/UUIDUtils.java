package me.skylertyler.scrimmage.utils;

import java.util.UUID;

public class UUIDUtils {

	public static UUID getUUIDFromString(String attribute) {
		UUID uuid = UUID.fromString(attribute);
		return uuid;
	}

}
