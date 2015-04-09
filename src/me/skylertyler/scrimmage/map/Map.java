package me.skylertyler.scrimmage.map;

import static org.bukkit.ChatColor.LIGHT_PURPLE;

import java.io.File;

public class Map {

	private final File xml;
	private final MapInfo info;

	public Map(File xml, MapInfo mapInfo) {
		this.xml = xml;
		this.info = mapInfo;

	}

	public boolean notNull() {
		return this != null;
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

	/**
	 * 
	 * @returns a short description about the map!
	 */
	//TODO work on Kits and the authors -_- 
	public String getShortMapDescription() {
		String currently = LIGHT_PURPLE + "Currently Playing";
		String name = getInfo().getName();
		String by = "by";
		// String author = StringUtils.listToEnglishCompound(getAuthors());
		String format = currently + " " + name + " " + by + " ";
		// author;
		return format;
	} 
}
