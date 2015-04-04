package me.skylertyler.scrimmage.regions;

public enum RegionType {

	BLOCK,POINT, EMPTY, CUBOID;

	public String toString() {
		switch (this) {
		case BLOCK:
			return "BlockRegion"; 
		case EMPTY:
			return "EmptyRegion";
		case POINT:
			return "PointRegion";
		case CUBOID:
			return "CuboidRegion";
		default:
			return null;
		}
	}
}
