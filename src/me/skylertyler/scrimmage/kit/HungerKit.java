package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class HungerKit {

	private final int hl;/** the hunger level */
	private final float s;  /** the saturation :) */

	public HungerKit(int hungerLevel, float saturation) {
		this.hl = hungerLevel;
		this.s = saturation;
	}

	public HungerKit() {
		this(20, 5.0F);
	}

	public int getHungerLevel() {
		return this.hl;
	}

	public float getSaturation() {
		return this.s;
	}

	public void apply(Player player) {
		player.setSaturation(getSaturation());
		player.setFoodLevel(getHungerLevel());
	}
}
