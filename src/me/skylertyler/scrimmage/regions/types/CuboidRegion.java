package me.skylertyler.scrimmage.regions.types;

import org.bukkit.util.Vector;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

public class CuboidRegion extends Region {

	private final BlockRegion max;
	private final BlockRegion min;

	public CuboidRegion(String name, BlockRegion min, BlockRegion max) {
		super(name);
		this.max = max;
		this.min = min;
	}

	public CuboidRegion(BlockRegion min, BlockRegion max) {
		super("");
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean containsVector(Vector vec) {
		return false;
	}

	@Override
	public RegionType getType() {
		return RegionType.CUBOID;
	}

	public BlockRegion getMax() {
		return this.max;
	}

	public BlockRegion getMin() {
		return this.min;
	}

}
