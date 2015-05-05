package me.skylertyler.scrimmage.utils;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtils {

	public static PotionEffect newPotionEffect(PotionEffectType type,
			int duration, int amplifier) {
		PotionEffect newEffect = new PotionEffect(type, duration, amplifier); 
		return newEffect;
	}

}
