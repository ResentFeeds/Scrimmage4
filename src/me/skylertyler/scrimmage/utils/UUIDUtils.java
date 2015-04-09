package me.skylertyler.scrimmage.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UUIDUtils {

	public static UUID getUUIDFromString(String attribute) {
		return UUID.fromString(attribute);

	}

	public static String getNameByUUID(UUID uuid) {
		try {
			JSONObject result = (JSONObject) new JSONParser()
					.parse(new InputStreamReader(new URL(
							"https://sessionserver.mojang.com/session/minecraft/profile/"
									+ uuid.toString().replace("-", ""))
							.openStream()));
			return (String) result.get("name");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
