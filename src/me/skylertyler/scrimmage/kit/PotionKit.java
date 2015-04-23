package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionKit {

	private PotionEffectType type;
	private int duration;
	private int amplifier;

	public PotionKit(PotionEffectType type, int duration, int amplifier) {
		this.type = type;
		this.duration = duration;
		this.amplifier = amplifier;
	}

	/** apply the potion kit as set in the XML */
	public void apply(Player player) {
		player.addPotionEffect(new PotionEffect(this.type, this.duration,
				this.amplifier));
	}
}
