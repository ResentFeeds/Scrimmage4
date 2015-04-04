package me.skylertyler.scrimmage.utils;

import org.bukkit.Material;
import org.w3c.dom.Element;

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

	public static boolean hasAttribute(Element element, String attribute) {
		return element.getAttribute(attribute) != null;
	}

	public static Material parseMaterial(String input) {
		for (Material mat : Material.values()) {
			if (mat.name().equalsIgnoreCase(input)) {
				return mat;
			}
		}

		return Material.getMaterial(input.toUpperCase().replaceAll(" ", "_"));
	}

}
