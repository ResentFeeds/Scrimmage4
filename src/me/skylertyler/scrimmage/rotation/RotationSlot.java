package me.skylertyler.scrimmage.rotation;

import me.skylertyler.scrimmage.map.Map;

public class RotationSlot {

	private final Map map;

	public RotationSlot(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return this.map;
	}
}
