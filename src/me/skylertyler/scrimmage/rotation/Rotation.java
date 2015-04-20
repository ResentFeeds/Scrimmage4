package me.skylertyler.scrimmage.rotation;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.config.types.Config;

public class Rotation {

	// will remove the RoatationConfig and replace it with this!
	private final List<RotationSlot> rotation;
	private Config config;

	public Rotation() {
		this.config = Scrimmage.getScrimmageInstance().getConfigFile();
		this.rotation = new ArrayList<RotationSlot>();
	}

	public List<RotationSlot> getRotation() {
		return this.rotation;
	}

	public boolean hasRotation() {
		return getRotation().size() > 0;
	}

	public Config getConfig() {
		return this.config;
	}

}
