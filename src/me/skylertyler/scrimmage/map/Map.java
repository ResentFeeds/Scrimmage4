package me.skylertyler.scrimmage.map;

import java.io.File;

public class Map {

	private final File xml;
	private final MapInfo info; 
 
	public Map(File xml, MapInfo mapInfo) {
		this.xml = xml;
		this.info = mapInfo;
	}
 
	/**
	 * 
	 * @return the xml file
	 */
	public File getXMLFile() {
		return this.xml;
	}

	/**
	 * 
	 * @return the map info for the current map !
	 */

	public MapInfo getInfo() {
		return this.info;
	} 
 
}
