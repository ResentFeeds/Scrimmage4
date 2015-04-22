package me.skylertyler.scrimmage.spawn;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.team.Team;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.google.common.base.Optional;

public class Spawn {

	private final Team team;
	private final Region region;
	private final Optional<Float> yaw;
	private final Optional<Float> pitch;
	private final Optional<BlockRegion> angle;
	private final Optional<String> kit;

	public Spawn(Team team, Region region, @Nullable String kit,
			@Nullable BlockRegion angle, @Nullable float yaw,
			@Nullable float pitch) {
		this.team = team;
		this.region = region;
		this.kit = Optional.fromNullable(kit);
		this.angle = Optional.fromNullable(angle);
		this.yaw = Optional.fromNullable(yaw);
		this.pitch = Optional.fromNullable(pitch);
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

	public Optional<BlockRegion> getAngle() {
		return this.angle;
	}

	public Optional<String> getKit() {
		return this.kit;
	}

	public Location onEye(Player player) {
		if (this.getAngle().isPresent() && this.getAngle().get() != null) {
			BlockRegion eyeREegion = this.getAngle().get();
			if (eyeREegion.getVector() != null) {
				Vector vector = eyeREegion.getVector();
				return new Location(player.getWorld(), vector.getX(),
						vector.getY(), vector.getZ());
			}
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
		setEyeLocation(player, location);
	}

	public void setEyeLocation(Player player, Location newEyeLocation) {
		Location oldEyeLocation = player.getEyeLocation();
		newEyeLocation = oldEyeLocation;
	}
}
