package me.skylertyler.scrimmage.kit;

import javax.annotation.Nullable;

import org.bukkit.Material;

public class KitItem {

	private final int slot;
	private int amount;
	private final Material mat;

	public KitItem(int slot, @Nullable int amount, Material mat) {
		this.slot = slot;
		this.amount = amount;
		if (this.amount == 0)
			this.amount = 1;
		this.mat = mat;
	}

	public int getSlot() {
		return this.slot;
	}

	public int getAmount() {
		return this.amount;
	}

	public Material getMaterial() {
		return this.mat;
	}
}
