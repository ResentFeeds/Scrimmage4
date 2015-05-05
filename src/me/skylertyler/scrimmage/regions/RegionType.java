package me.skylertyler.scrimmage.regions;

public enum RegionType {

	BLOCK("BlockRegion"), POINT("PointRegion"), EMPTY("EmptyRegion"), CUBOID(
			"CuboidRegion"), SPHERE("SphereRegion"), CYLINDER("CylinderRegion"), CIRCLE(
			"CircleRegion");

	private final String name;

	RegionType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		if (this.getName().contains("_")) {
			return this.getName().replace("_", " ").toLowerCase();
		} else {
			return this.getName().toLowerCase();
		}
	}
}
