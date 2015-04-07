package me.skylertyler.scrimmage.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.w3c.dom.Element;

public class XMLUtils {

	// my own booleam parsing (i like this better because you can have enabled,
	// on , true , etc instead of just true or false
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

	// basically do the same thing like above as shown -_- but instead of doing
	// material You're doing a enchantment
	public static Enchantment parseEnchantment(String string) {
		for (Enchantment enchant : Enchantment.values()) {
			if (enchant.getName().equalsIgnoreCase(string)) {
				return enchant;
			}
		}
		return Enchantment.getByName(string.toUpperCase().replace(" ", "_"));
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

}
