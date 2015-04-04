package me.skylertyler.scrimmage.regions;

public enum RegionType {

	BLOCK("BlockRegion"), POINT("PointRegion"), EMPTY("EmptyRegion"), CUBOID(
			"CuboidRegion"), SPHERE("SphereRegion");

	private final String name;

	RegionType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.getName();
	}
}
