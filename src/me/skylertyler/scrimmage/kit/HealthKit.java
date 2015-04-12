package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class HealthKit {

	private final double mh;// max health
	private final double sh;// starting health

	/**
	 * 
	 * @param maxHealth
	 *            the max health for the player
	 * @param startingHealth
	 *            the starting health for the player when they get the kit
	 */
	public HealthKit(double maxHealth, double startingHealth) {
		this.mh = maxHealth;
		this.sh = startingHealth;
	}

	public HealthKit() {
		this(20, 20);
	}

	public double getMaxHealth() {
		return this.mh;
	}

	public double getStartingHealth() {
		return this.sh;
	}

	public void apply(Player player) {
		 player.setMaxHealth(getMaxHealth());
		 player.setHealth(getStartingHealth());
	}
}
