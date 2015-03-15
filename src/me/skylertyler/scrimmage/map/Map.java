package me.skylertyler.scrimmage.map;

import java.io.File;

public class Map {

	protected File xml;
	protected MapInfo info;
	public Map(File xml, MapInfo info) {
		this.xml = xml;
		this.info = info;
	}

	public File getXMLFile() {
		return this.xml;
	}

	public MapInfo getInfo() {
		return this.info;
	} 
}
