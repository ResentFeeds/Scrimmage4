package me.skylertyler.scrimmage.rotation;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.config.types.Config;
import me.skylertyler.scrimmage.map.Map;

public class Rotation {
 
	private final List<Map> rotation;
	private final Config config;

	public Rotation() {
		this.config = Scrimmage.getScrimmageInstance().getConfigFile();
		this.rotation = new ArrayList<>();
	}

	public List<Map> getRotation() {
		return this.rotation;
	}

	public boolean hasRotation() {
		return getRotation().size() > 0;
	}

	public Config getConfig() {
		return this.config;
	} 
}
