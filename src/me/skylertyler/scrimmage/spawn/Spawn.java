package me.skylertyler.scrimmage.spawn;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.team.Team;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.google.common.base.Optional;

public class Spawn {

	private final Team team;
	/** the team who the spawn belongs to (required) */
	private final Region region;
	/** the region (required) */
	private final Optional<Float> yaw;
	private final Optional<Float> pitch;
	private final Optional<Location> angle;
	/** the location where the players eye will be looking at */
	private final Optional<String> kit;
	/** the kit for the spawn */
	private Location eyeLocation;
	/** the players eye location */

	private boolean bedspawn;

	/** allow players to spawn at their beds */

	public Spawn(Team team, Region region, @Nullable String kit,
			@Nullable Location angle, @Nullable float yaw,
			@Nullable float pitch, boolean bedspawn) {
		this.team = team;
		this.region = region;
		this.kit = Optional.fromNullable(kit);
		this.angle = Optional.fromNullable(angle);
		this.yaw = Optional.fromNullable(yaw);
		this.pitch = Optional.fromNullable(pitch);
		this.bedspawn = bedspawn;
	}

	public Team getTeam() {
		return this.team;
	}

	public Region getRegion() {
		return this.region;
	}

	public Optional<Float> getYaw() {
		return this.yaw;
	}

	public Optional<Float> getPitch() {
		return this.pitch;
	}

	public Optional<Location> getAngle() {
		return this.angle;
	}

	public boolean hasAngle() {
		return this.getAngle().isPresent() && this.getAngle().get() != null;
	}

	public Optional<String> getKit() {
		return this.kit;
	}

	public boolean isBedSpawnAllowed() {
		return this.bedspawn;
	}

	public Location onEye(Player player) {
		if (this.getAngle().isPresent() && this.getAngle().get() != null) {
			Location eyeRegion = this.getAngle().get();
			return eyeRegion;
		}
		return null;
	}

	public void spawn(Player player) {
		Region region = this.getRegion();

		if (region instanceof BlockRegion) {
			BlockRegion blockRegion = (BlockRegion) region;
			player.teleport(new Location(player.getWorld(), blockRegion
					.getVector().getX(), blockRegion.getVector().getY(),
					blockRegion.getVector().getZ()));
		}

		Location location = this.onEye(player);
		setEyeLocation(player.getEyeLocation(), location);
	}

	/** set the player eye location */
	public void setEyeLocation(Location eyeLocation, Location newEyeLocation) {
		this.eyeLocation = eyeLocation;
		this.eyeLocation = newEyeLocation;
	}

	public Location getEyeLocation() {
		return this.eyeLocation;
	}
}
