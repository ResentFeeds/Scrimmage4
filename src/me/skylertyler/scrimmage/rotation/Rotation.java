package me.skylertyler.scrimmage.rotation;

import java.util.ArrayList;
import java.util.List;
 

public class Rotation {

	private  List<RotationSlot> rotation = null;

	public Rotation() {
		this.rotation = new ArrayList<RotationSlot>();
	}

	public List<RotationSlot> getRotation() {
		return this.rotation;
	} 
	
	public boolean hasRotation(){
		return getRotation().size() != 0;
	}
	 
}
