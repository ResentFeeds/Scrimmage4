package me.skylertyler.scrimmage.regions;

import org.bukkit.util.Vector;

public abstract class Region {

	private final String name;

	public Region(String name) {
		this.name = name;
	}

	public abstract boolean containsVector(Vector vec);

	public String getName() {
		return this.name;
	}

	public boolean hasName() {
		return this.name != "";
	}

	public abstract RegionType getType();

}
