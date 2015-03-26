package me.skylertyler.scrimmage.map;

import java.io.File;

import me.skylertyler.scrimmage.Scrimmage;

public class Map {

	private File xml;
	private MapInfo info;
	private Scrimmage scrim;
	private File source;
	private File path;
	private String DEFUALT_XML = "map.xml";

	public Map(File source, MapInfo mapInfo) {
		this.source = source;
		this.info = mapInfo;
		this.xml = new File(source, DEFUALT_XML);
	}

	public String getDefualtXML() {
		return this.DEFUALT_XML;
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

	public File getXml() {
		return xml;
	}

	public Scrimmage getScrim() {
		return scrim;
	}

	public File getSource() {
		return source;
	}

	public File getPath() {
		return path;
	}

}
