package me.skylertyler.scrimmage.kit;

import me.skylertyler.scrimmage.utils.PotionUtils;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionKit {

	private PotionEffectType type;
	private int duration;
	private int amplifier;
	//private boolean ambient;

	public PotionKit(PotionEffectType type, int duration, int amplifier) {
		this.type = type;
		this.duration = duration;
		this.amplifier = amplifier;
		//this.ambient = ambient;
	}

	/** apply the potion kit as set in the XML */
	public void apply(Player player) {
		PotionEffect effect = PotionUtils.newPotionEffect(this.type, this.duration, this.amplifier);
		player.addPotionEffect(effect);
	}
}
