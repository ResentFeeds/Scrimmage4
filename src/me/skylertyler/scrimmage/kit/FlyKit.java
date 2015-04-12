package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class FlyKit {

	private final boolean flying;
	private final float speed;

	// TODO
	public FlyKit(boolean flying, float speed) {
		this.flying = flying;
		this.speed = speed;
	}

	public float getSpeed() {
		return this.speed;
	}

	public void apply(Player player) {
		player.setFlying(isFlying());
		player.setFlySpeed(getSpeed());
	}

	public boolean isFlying() {
		return this.getFlying() == true;
	}

	public boolean getFlying() {
		return this.flying;
	}
}
