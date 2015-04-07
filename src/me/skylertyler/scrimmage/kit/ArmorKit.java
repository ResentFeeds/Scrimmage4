package me.skylertyler.scrimmage.kit;

import org.bukkit.inventory.ItemStack;

public class ArmorKit {

	private ItemStack helmet;
	private ItemStack chestplate;
	private ItemStack leggings;
	private ItemStack boots;

	//TODO add this to the main Kit class :)
	public ArmorKit(ItemStack helmet, ItemStack chestplate, ItemStack leggings,
			ItemStack boots) {
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
	}

	public ItemStack getHelmet() {
		return helmet;
	}

	public ItemStack getChestplate() {
		return chestplate;
	}

	public ItemStack getLeggings() {
		return leggings;
	}

	public ItemStack getBoots() {
		return boots;
	}
}
