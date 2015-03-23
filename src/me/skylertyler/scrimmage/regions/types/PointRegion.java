package me.skylertyler.scrimmage.regions.types;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

import org.bukkit.util.Vector;

public class PointRegion extends Region {

	private final Vector point;

	public PointRegion(Vector point, String name) {
		super(name);
		this.point = point;
	}

	@Override
	public boolean containsVector(Vector vec) {
		Vector vector = this.getVector();
		return vector.getX() == vec.getX() && vector.getY() == vec.getY()
				&& vec.getZ() == vec.getZ();
	}

	@Override
	public RegionType getType() {
		return RegionType.POINT;
	}

	public Vector getVector() {
		return this.point;
	}
}
