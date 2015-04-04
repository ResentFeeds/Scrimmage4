package me.skylertyler.scrimmage.kit;

import javax.annotation.Nullable;

import org.bukkit.potion.PotionEffect;

public class KitPotion {

	private final PotionEffect effect;
	private final int duration;
	private final int amplifier;

	@Nullable
	public KitPotion(int duration, int amplifier, PotionEffect effect) {
		this.duration = duration;
		this.amplifier = amplifier;
		this.effect = effect;
	}

	public int getAmplifier() {
		return this.amplifier;
	}

	public PotionEffect getEffect() {
		return this.effect;
	}

	public int getDuration() {
		return this.duration;
	}
}
