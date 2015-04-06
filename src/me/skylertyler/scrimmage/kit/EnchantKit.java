package me.skylertyler.scrimmage.kit;

import java.util.HashMap;
import java.util.Map;

import me.skylertyler.scrimmage.exception.EnchantNotFoundException;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.enchantments.Enchantment;

public class EnchantKit {

	private final String enchantment;

	private final Map<Enchantment, Integer> enchantments;

	public EnchantKit(String enchantment) {
		this.enchantments = new HashMap<>();
		this.enchantment = enchantment;
		// if it has a enchantment do this below -_-
		if (hasEnchantment()) {
			String[] split_enchantment = this.enchantment.split(":");
			Enchantment enchant = XMLUtils
					.parseEnchantment(split_enchantment[0]);
			if (enchant == null) {
				try {
					throw new EnchantNotFoundException(split_enchantment[0]);
				} catch (EnchantNotFoundException e) {
					e.printStackTrace();
				}
			}
			Integer level = NumberUtils.parseInteger(split_enchantment[1]);
			this.enchantments.put(enchant, level);
		}
	}

	public String getEnchantment() {
		return this.enchantment;
	}

	public boolean hasEnchantment() {
		return this.getEnchantment() != null;
	}

	public Map<Enchantment, Integer> getEnchantments() {
		return this.enchantments;
	}
}
