package me.skylertyler.scrimmage.regions.types;

import org.bukkit.util.Vector;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

public class SphereRegion extends Region {

	private final int radius;
	private final BlockRegion origin;

	public SphereRegion(String name, BlockRegion origin, int radius) {
		super(name);
		this.origin = origin;
		this.radius = radius;
	}

	public SphereRegion(BlockRegion origin, int radius) {
		this("", origin, radius);
	}

	@Override
	public boolean containsVector(Vector vec) {
		return vec.isInSphere(getOrigin().getVector(), getRadius());
	}

	@Override
	public RegionType getType() {
		return RegionType.SPHERE;
	}

	public int getRadius() {
		return radius;
	}

	public BlockRegion getOrigin() {
		return origin;
	}

}
