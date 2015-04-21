package me.skylertyler.scrimmage.kit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.enchantments.Enchantment;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class EnchantKit {

	private String enchant;
	private Map<Enchantment, Integer> enchantments;

	//TODO fix this -_- not adding (enchantments to items) and make enchantments be applied to what ever item they want */
	public EnchantKit(String enchant) {
		this.enchant = enchant;
		this.enchantments = new HashMap<Enchantment, Integer>();
        
		Iterable<String> split_enchantment = Splitter.on(";").split(
				this.enchant);

		for (String string : split_enchantment) {
			List<String> enchants = Lists.newArrayList(Splitter.on(":")
					.limit(2).split(string));
			int level = 1;
			Enchantment newEnchant = XMLUtils.parseEnchantment(enchants.get(0));
			if (enchants.size() > 1) {
				level = NumberUtils.parseInteger(enchants.get(1));
			}
			enchantments.put(newEnchant, level);
		}

	}

	public String getEnchantment() {
		return this.enchant;
	}

	public Map<Enchantment, Integer> getEnchantments() {
		return this.enchantments;
	}
}
