package me.skylertyler.scrimmage.kit;

import java.util.HashMap;
import java.util.Map;

import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.enchantments.Enchantment;

public class EnchantKit {

	private final String enchantment;
	private final Map<Enchantment, Integer> enchantments;
	private String[] split_enchantment;
	private int length;

	public EnchantKit(String enchantment) {
		this.enchantments = new HashMap<Enchantment, Integer>();
		this.enchantment = enchantment;
		if (hasEnchantment()) {
			this.split_enchantment = this.enchantment.split(":");
			this.length = this.split_enchantment.length;
			Integer level = 1;
			Enchantment enchant = null;
			if (hasLength()) {
				if (getLength() == 1) {
					enchant = XMLUtils.parseEnchantment(split()[0]);
				} else {
					if (getLength() >= 2) {
						enchant = XMLUtils.parseEnchantment(split()[0]);
						level = NumberUtils.parseInteger(split()[1]);
					}
				}
				this.enchantments.put(enchant, level);
			}
		}
	}

	public int getLength() {
		return this.length;
	}

	public boolean hasLength() {
		return this.split_enchantment.length > 0;
	}

	public String[] split() {
		return this.split_enchantment;
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
