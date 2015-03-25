package me.skylertyler.scrimmage.test;

import java.util.HashMap;

public class TestLoader {

	// FIX the current map using all the /map.xml files! from all the maps 
	// looking for a better way to load the maps :) (so if you got an idea plz tell me :) )
	private HashMap<TestInfo, TestMap> loadedMaps = null;

	public TestLoader() {
		this.setLoadedMaps(new HashMap<TestInfo, TestMap>());
	}

	public HashMap<TestInfo, TestMap> getLoadedMaps() {
		return loadedMaps;
	}

	public void setLoadedMaps(HashMap<TestInfo, TestMap> loadedMaps) {
		this.loadedMaps = loadedMaps;
	}

}
