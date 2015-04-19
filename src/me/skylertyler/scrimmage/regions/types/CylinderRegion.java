package me.skylertyler.scrimmage.regions.types;

import org.bukkit.util.Vector;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

public class CylinderRegion extends Region {

	private final BlockRegion base;
	private final int radius;
	private final int height;
	private int rSquared;

	public CylinderRegion(String name, BlockRegion base, int radius, int height) {
		super(name);
		this.base = base;
		this.radius = radius;
		this.height = height;
		this.rSquared = this.radius * this.radius;
	}

	public CylinderRegion(BlockRegion base, int radius, int height) {
		this("", base, radius, height);
	}

	@Override
	public boolean containsVector(Vector vec) {
		double newX = (getBase().getVector().getX() - vec.getX());
		double newZ = (getBase().getVector().getZ() - vec.getZ());
		boolean h = vec.getY() <= getBase().getVector().getY() + getHeight()
				&& vec.getY() >= getBase().getVector().getY();
		return h && newX * newX + newZ * newZ < this.rSquared;
	}

	@Override
	public RegionType getType() {
		return RegionType.CYLINDER;
	}

	public BlockRegion getBase() {
		return base;
	}

	public int getRadius() {
		return radius;
	}

	public int getHeight() {
		return height;
	}

}
