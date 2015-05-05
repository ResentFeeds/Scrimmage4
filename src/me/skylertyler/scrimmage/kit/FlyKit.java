package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class FlyKit {

	private final boolean flying;
	private final float speed;
 
	public FlyKit(boolean flying, float speed) {
		this.flying = flying;
		this.speed = speed;
	}

	public float getSpeed() {
		return this.speed;
	}

	public void apply(Player player) {
		player.setFlying(this.flying);
		player.setFlySpeed(this.speed);
	}

	public boolean isFlying() {
		return this.getFlying();
	}

	public boolean getFlying() {
		return this.flying;
	}
}
