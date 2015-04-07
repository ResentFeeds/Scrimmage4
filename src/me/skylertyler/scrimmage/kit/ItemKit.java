package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.utils.BukkitUtils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemKit {

	private final int damage;
	private final String name;
	private final String lore;
	private final ItemStack stack;
	private final int slot;
	private final EnchantKit enchant;
	private final ItemMeta itemMeta;

	public ItemKit(int slot, ItemStack stack, int damage, String name,
			String lore, EnchantKit enchant) {
		this.slot = slot;
		this.stack = stack;
		this.itemMeta = getStack().getItemMeta();
		this.damage = damage;
		this.name = name;
		this.lore = lore;
		this.enchant = enchant;

		if (hasEnchant()) {
			getStack().addEnchantments(getEnchant().getEnchantments());
		}
		if (this.damage != 0) {
			getStack().setDurability((short) this.getDamage());
		}

		// works fine ;)
		String newName = null;
		if (hasName()) {
			newName = parseName(getName());
		} else {
			newName = this.getStack().getType().name().replace("_", " ")
					.toLowerCase();
		}

		// fix lore -_- 
		List<String> lores = null;
		if (hasLore()) {
			lores = parseLore(getLore());
		} else {
			lores = new ArrayList<>();
		}

		getItemMeta().setDisplayName(newName);
		getItemMeta().setLore(lores);
		getStack().setItemMeta(getItemMeta());
	}

	public boolean hasName() {
		return this.getName() != null;
	}

	public boolean hasLore() {
		return this.getLore() != null;
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

	public ItemStack getStack() {
		return this.stack;
	}

	public int getSlot() {
		return this.slot;
	}

	public void apply(Player player) {
		PlayerInventory pi = player.getInventory();
		int slot = this.getSlot();
		ItemStack stack = this.getStack();
		pi.setItem(slot, stack);
	}

	public EnchantKit getEnchant() {
		return this.enchant;
	}

	public boolean hasEnchant() {
		return this.enchant != null;
	}

	public ItemMeta getItemMeta() {
		return this.itemMeta;
	}

	public List<String> parseLore(String lore) {
		List<String> all = new ArrayList<>();
		String[] lores = lore.split("|");
		String newLine = null;
		for (String line : lores) {
			newLine = line;
		}
		all.add(newLine);
		return all;
	}

	public String parseName(String name) {
		boolean hasColor = name.contains("`");
		String format = null;
		if (hasColor) {
			format = BukkitUtils.colorize(name);
		} else {
			format = name;
		}

		String result = format;
		return result;
	}
}
