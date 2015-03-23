package me.skylertyler.scrimmage.regions.types;

import org.bukkit.util.Vector;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

public class EmptyRegion extends Region {
	public EmptyRegion(String name) {
		super(name);
	} 

	@Override
	public boolean containsVector(Vector vec) {
		return vec == null;
	}

	@Override
	public RegionType getType() {
		return RegionType.EMPTY;
	}
}
