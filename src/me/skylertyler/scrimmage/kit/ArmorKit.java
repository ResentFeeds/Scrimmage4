package me.skylertyler.scrimmage.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmorKit {

	private final ItemStack helmet;
	private final ItemStack chestplate;
	private final ItemStack leggings;
	private final ItemStack boots;

	// TODO add this to the main Kit class :)
	public ArmorKit(ItemStack helmet, ItemStack chestplate, ItemStack leggings,
			ItemStack boots) {
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
	}

	public ItemStack getHelmet() {
		return this.helmet;
	}

	public Material getHelmetMaterial() {
		return this.getHelmet().getType();
	}

	public ItemStack getChestplate() {
		return this.chestplate;
	}

	public Material getChestplateMaterial() {
		return getChestplate().getType();
	}

	public ItemStack getLeggings() {
		return this.leggings;
	}

	public Material getLeggingsMaterial() {
		return getLeggings().getType();
	}

	public ItemStack getBoots() {
		return this.boots;
	}

	public Material getBootsMaterial() {
		return getBoots().getType();
	}

	/**
	 * 
	 * @param player
	 *            the player to give the armor to.
	 */
	public void apply(Player player) {
		PlayerInventory pi = player.getInventory();
		ItemStack helmet = this.getHelmet();
		ItemStack chestplate = this.getChestplate();
		ItemStack leggings = this.getLeggings();
		ItemStack boots = this.getBoots();
		boolean full = helmet != null && chestplate != null && leggings != null
				&& boots != null;

		if (full) {
			pi.setHelmet(helmet);
			pi.setChestplate(chestplate);
			pi.setLeggings(leggings);
			pi.setBoots(boots);
		} else {
			
			if (helmet != null || chestplate != null || leggings != null
					|| boots != null) {
				pi.setHelmet(helmet);
				pi.setChestplate(chestplate);
				pi.setLeggings(leggings);
				pi.setBoots(boots);
			}
		}
	}
}
