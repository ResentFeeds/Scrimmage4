package me.skylertyler.scrimmage.kit;

import java.util.HashMap;
import java.util.Map;

import me.skylertyler.scrimmage.exception.EnchantNotFoundException;
import me.skylertyler.scrimmage.exception.InvalidEnchantmentLevelException;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.enchantments.Enchantment;

public class EnchantKit {

	private final String enchantment;

	private Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();

	public EnchantKit(String enchantment) { 
		this.enchantment = enchantment;
		// if it has a enchantment do this below -_-
		if (hasEnchantment()) {
			int level = 1;
			String[] split_enchantment = this.enchantment.split(":");
			Enchantment enchant = XMLUtils
					.parseEnchantment(split_enchantment[0]);
			if (enchant == null) {
				try {
					throw new EnchantNotFoundException(split_enchantment[0]);
				} catch (EnchantNotFoundException e) {
					Log.logWarning(e.getMessage());
				}
			}

			// if the level part of the enchantment attribute is not null do this below :)
			// else make the level 1 by default
			if (split_enchantment[1] != null) {
				level = NumberUtils.parseInteger(split_enchantment[1]);
				boolean invalid = level <= 0;
				if (invalid) {
					try {
						throw new InvalidEnchantmentLevelException(level,
								split_enchantment[0]);
					} catch (InvalidEnchantmentLevelException e) {
						e.printStackTrace();
					}
				}
			}
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
