package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import me.skylertyler.scrimmage.utils.BukkitUtils;
import me.skylertyler.scrimmage.utils.NumberUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemKit {

	private Material mat;
	private int amount;
	private int damage;
	private String name;
	private String lore;
	private String enchantment;
	private HashMap<Integer, Enchantment> enchantments;
	private ItemStack stack;
	private ItemMeta meta;
	private int slot;

	public ItemKit(int slot, Material mat, int amount, int damage, String name,
			String lore, String enchantment) {
		extracted(slot, mat, amount, damage, name, lore, enchantment);
	}

	private void extracted(int slot, Material mat, int amount, int damage,
			String name, String lore, String enchantment) {
		this.enchantments = new HashMap<>();
		this.slot = slot;
		this.mat = mat;
		this.amount = amount;
		this.damage = damage;
		this.name = name;
		this.lore = lore; 

		if (this.amount == 0)
			this.amount = 1;

		this.stack = new ItemStack(mat);
		this.meta = this.stack.getItemMeta();

		// TODO fix lore -_- and enchantments :)
		// fix only doing on item on a kit !
		if (this.enchantment != null) {
			String[] split_enchantment = this.enchantment.split(":");
			Enchantment enchant = XMLUtils
					.parseEnchantment(split_enchantment[0]);
			Integer level = NumberUtils.parseInteger(split_enchantment[1]);
			this.enchantments.put(level, enchant);
		}

		for (Entry<Integer, Enchantment> enchantments : getEnchantments()
				.entrySet()) {
			if (enchantments != null) {
				this.stack.addEnchantment(enchantments.getValue(), enchantments.getKey());
			}
		}
		if (this.lore != null) {
			String[] split_lore = this.lore.split("|");
			List<String> ll = new ArrayList<>();
			String result = null;
			for (String string : split_lore) {
				if (string.contains("`")) {
					result = BukkitUtils.colorize(string);
				} else {
					result = string;
				}
				ll.add(result);
			}

			this.meta.setLore(ll);
		}

		if (this.name != null) {
			String result = null;
			if (this.name.contains("`")) {
				result = BukkitUtils.colorize(getName());
			} else {
				result = getName();
			}
			this.meta.setDisplayName(result);
		}

		if (this.damage != 0) {
			this.stack.setDurability((short) this.getDamage());
		}

		this.stack.setItemMeta(getMeta());
		// TODO
	}

	public Material getMaterial() {
		return this.mat;
	}

	public int getAmount() {
		return this.amount;
	}

	public int getDamage() {
		return this.damage;
	}

	public String getName() {
		return this.name;
	}

	public String getLore() {
		return this.lore;
	}

	public String getEnchantment() {
		return this.enchantment;
	}

	public HashMap<Integer, Enchantment> getEnchantments() {
		return this.enchantments;
	}

	public ItemStack getStack() {
		return this.stack;
	}

	public ItemMeta getMeta() {
		return this.meta;
	}

	public int getSlot() {
		return this.slot;
	}
}
