package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class KnockbackReductionKit {

	protected final float reduction;

	public KnockbackReductionKit(float reduction) {
		this.reduction = reduction;
	}

	/** set the knock back reduction for the player */
	public void apply(Player player) {
		player.setKnockbackReduction(this.getReduction());
	}

	public float getReduction() {
		return this.reduction;
	}
}
