package me.skylertyler.scrimmage.regions.types;

import org.bukkit.util.Vector;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

public class CircleRegion extends Region {

	private final BlockRegion center;
	private final int radius;
	private final int rSquared;

	public CircleRegion(String name, BlockRegion center, int radius) {
		super(name);
		this.center = center;
		this.radius = radius;
		this.rSquared = this.radius * this.radius;
	}

	public CircleRegion(BlockRegion center, int radius) {
		this("", center, radius);
	}

	public BlockRegion getCenter() {
		return this.center;
	}

	public int getRadius() {
		return this.radius;
	}

	@Override
	public boolean containsVector(Vector vec) {
		double newX = (getCenter().getVector().getX() - vec.getX());
		double newZ = (getCenter().getVector().getZ() - vec.getZ());
		return newX * newX + newZ * newZ < rSquared;
	}

	@Override
	public RegionType getType() {
		return RegionType.CIRCLE;
	}
}
