package me.skylertyler.scrimmage.regions.types;

import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionType;
 
import org.bukkit.util.Vector;

public class BlockRegion extends Region {

	public Vector vector;

	public BlockRegion(Vector vector, String name) {
		super(name);
		this.vector = vector;
	}

	public BlockRegion(Vector vector) {
		this(vector, "");
	}

	@Override
	public boolean containsVector(Vector vec) {
		return this.vector.getX() == vec.getX()
				&& this.vector.getY() == vec.getY()
				&& this.vector.getZ() == vec.getZ();
	} 
 

	public Vector getVector() {
		return this.vector;
	}

	public RegionType getType() {
		return RegionType.BLOCK;
	}

}
