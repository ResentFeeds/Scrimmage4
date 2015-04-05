package me.skylertyler.scrimmage.spawn;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.team.Team;

public class Spawn {

	private final Team team;
	private final Region region;
	private final float yaw;
	private final float pitch;
	private final BlockRegion angle;
	private final Kit kit;

	public Spawn(Team team, Region region, @Nullable Kit kit,
			@Nullable BlockRegion angle, @Nullable float yaw,
			@Nullable float pitch) {
		this.team = team;
		this.region = region;
		this.kit = kit;
		this.angle = angle;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public BlockRegion getAngle() {
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

	public Kit getKit() {
		return this.kit;
	}

	public boolean hasKit() {
		return this.getKit() != null;
	}

	public boolean hasAngle() {
		return this.getAngle() != null;
	}
}
