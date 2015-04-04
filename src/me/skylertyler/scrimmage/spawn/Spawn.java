package me.skylertyler.scrimmage.spawn;
 

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.team.Team;

public class Spawn {

	private final Team team;
	private final Region region;
	private final float yaw;
	private final float pitch;
	private final BlockRegion angle;
	
	public Spawn(Team team, Region region, BlockRegion angle, float yaw, float pitch) {
		this.team = team;
		this.region = region;
		this.angle = angle;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	
	public BlockRegion getAngle(){
		return this.angle;
	}

	public Team getTeam() {
		return this.team;
	}

	public Region getRegion() {
		return this.region;
	}

	public float getYaw() {
		return this.yaw;
	}

	public float getPitch() {
		return this.pitch;
	}
}
