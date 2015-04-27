package me.skylertyler.scrimmage.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class OvercastUtil {

	public static String OVERCAST_URL = "https://oc.tc/";

	public static String getOvercastURL() {
		return OVERCAST_URL;
	}

	public static URL toURL(String input) throws MalformedURLException {
		return new URL(input);
	}
}
