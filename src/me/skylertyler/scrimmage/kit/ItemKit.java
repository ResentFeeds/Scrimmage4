package me.skylertyler.scrimmage.kit;

import java.util.ArrayList;
import java.util.List;
import me.skylertyler.scrimmage.utils.BukkitUtils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemKit {

	private Material mat;
	private int amount;
	private int damage;
	private String name;
	private String lore;
	private ItemStack stack;
	private ItemMeta meta;
	private int slot;
	private EnchantKit enchant;

	public ItemKit(int slot, Material mat, int amount, int damage, String name,
			String lore, EnchantKit enchant) {
		extracted(slot, mat, amount, damage, name, lore, enchant);
	}

	private void extracted(int slot, Material mat, int amount, int damage,
			String name, String lore, EnchantKit enchant) {
		this.slot = slot;
		this.mat = mat;
		this.amount = amount;
		this.damage = damage;
		this.name = name;
		this.lore = lore;
		this.enchant = enchant;
		if (this.amount == 0)
			this.amount = 1;

		this.stack = new ItemStack(mat);
		this.meta = this.stack.getItemMeta();

		// TODO fix lore -_- and enchantments :)
		// fix only doing on item on a kit !
		if (this.lore != null) {
			String[] lorelist = lore.split("|");
			List<String> ll = new ArrayList<>();
			for (String str : lorelist) {
				ll.add(BukkitUtils.colorize(str));
			}
			this.meta.setLore(ll);
		}

		if (hasEnchant()) {
			this.stack.addEnchantments(getEnchant().getEnchantments());
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

	public ItemStack getStack() {
		return this.stack;
	}

	public ItemMeta getMeta() {
		return this.meta;
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
}
