package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class KnockbackReductionKit {

	private final float reduction;

	public KnockbackReductionKit(float reduction) {
		this.reduction = reduction;
	}

	public float getReduction() {
		return this.reduction;
	}

	public void apply(Player player) {
		player.setKnockbackReduction(getReduction());
	}
}
