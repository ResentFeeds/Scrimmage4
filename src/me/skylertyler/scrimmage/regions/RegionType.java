package me.skylertyler.scrimmage.regions;

public enum RegionType {

	BLOCK,POINT, EMPTY;

	public String toString() {
		switch (this) {
		case BLOCK:
			return "BlockRegion"; 
		case EMPTY:
			return "EmptyRegion";
		case POINT:
			return "PointRegion";
		default:
			return null;
		}
	}
}
