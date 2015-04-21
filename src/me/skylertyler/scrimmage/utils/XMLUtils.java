package me.skylertyler.scrimmage.utils;

import java.awt.Color;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.w3c.dom.Element;

public class XMLUtils {

	// my own booleam parsing (i like this better because you can have enabled,
	// on , true , yes etc instead of just true or false
	public static boolean parseBoolean(String result) {
		switch (result) {
		case "enabled":
		case "on":
		case "yes":
		case "true":
			return true;
		case "disabled":
		case "off":
		case "no":
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

	public static Enchantment parseEnchantment(String value) {
		String uppercase = value.toUpperCase().replace(" ", "_"); 
		Enchantment enchantment = Enchantment.getByName(uppercase);
		return enchantment;
	}

	public static boolean isValidArmorTag(Element element) {
		switch (element.getNodeName()) {
		case "helmet":
		case "chestplate":
		case "leggings":
		case "boots":
			return true;
		default:
			return false;
		}
	}

	public static Color hex2Rgb(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16),
				Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(
						colorStr.substring(5, 7), 16));
	}

	public static org.bukkit.Color applyColor(String color) {
		Color javaColor = hex2Rgb("#" + color);
		return org.bukkit.Color.fromRGB(javaColor.getRed(),
				javaColor.getGreen(), javaColor.getBlue());
	}
}
