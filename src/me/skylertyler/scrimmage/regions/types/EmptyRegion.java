package me.skylertyler.scrimmage.regions.types;

import org.bukkit.util.Vector;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;

public class EmptyRegion extends Region {

	// will change this -_-
	public EmptyRegion(String name) {
		super(name);
	}

	public EmptyRegion() {
		this("");
	}

	@Override
	public boolean containsVector(Vector vec) {
		return false;
	}

	@Override
	public RegionType getType() {
		return RegionType.EMPTY;
	}
}
