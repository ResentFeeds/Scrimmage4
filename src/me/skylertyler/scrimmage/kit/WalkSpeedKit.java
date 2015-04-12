package me.skylertyler.scrimmage.kit;

import org.bukkit.entity.Player;

public class WalkSpeedKit {

	private final float speed;

	public WalkSpeedKit(float speed) {
		this.speed = speed;
	}
	
	
	public WalkSpeedKit(){
		this(0f);
	}

	public float getSpeed() {
		return this.speed;
	}
	
	
	public void apply(Player player){
		player.setWalkSpeed(getSpeed());
	}
}
