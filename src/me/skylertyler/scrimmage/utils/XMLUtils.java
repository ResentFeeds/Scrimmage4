package me.skylertyler.scrimmage.utils;

public class XMLUtils {
	
	public static boolean parseBoolean(String result) {
		switch (result) {
		case "enabled":
		case "on":
		case "true":
			return true;
		case "disabled":
		case "off":
		case "false":
			return false;
		default:
			return false;
		}
	}

}
