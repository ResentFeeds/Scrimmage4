package me.skylertyler.scrimmage.map;

import static org.bukkit.ChatColor.*;

import java.io.File;

import me.skylertyler.scrimmage.utils.StringUtils;

public class Map {

	private final File xml;
	private final File source;
	private final String sourceName;
	private final MapInfo info;

	public Map(File xml, File source, MapInfo mapInfo) {
		this.xml = xml;
		this.source = source;
		this.sourceName = this.source.getName();
		this.info = mapInfo;
	}

	public File getSource() {
		return this.source;
	}

	public String getSourceName() {
		return this.sourceName;
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
	// TODO work on Kits
	public String getShortMapDescription() {
		String currently = LIGHT_PURPLE + "Currently Playing";
		String name = getInfo().getName();
		String by = "by";
		String author = StringUtils.listToEnglishCompound(getInfo()
				.getAuthorNames().keySet(), RED + "", LIGHT_PURPLE + "");
		String format = currently + " " + name + " " + by + " " + author;
		return format;
	}

	public String getNextMapDescription() {
		String currently = DARK_PURPLE + "Next Map:";
		String name = GOLD + getInfo().getName();
		String by = DARK_PURPLE + "by";
		String author = StringUtils.listToEnglishCompound(getInfo()
				.getAuthorNames().keySet(), RED + "", DARK_PURPLE + "");
		String format = currently + " " + name + " " + by + " " + author;
		return format;
	}

	public String getMapDescription() {
		String name = GOLD + getInfo().getName();
		String by =  DARK_PURPLE + "by";
		String author = StringUtils.listToEnglishCompound(getInfo()
				.getAuthorNames().keySet(), RED + "", DARK_PURPLE + "");
		String format = name + " " + by + " " + author;
		return format;
	}
}
