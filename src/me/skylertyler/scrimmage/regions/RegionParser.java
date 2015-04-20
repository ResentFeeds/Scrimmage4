package me.skylertyler.scrimmage.regions;

import java.util.HashMap;

import me.skylertyler.scrimmage.parsers.ElementParser;

import org.w3c.dom.Element;

public class RegionParser extends ElementParser {

	private HashMap<String, Region> regions;
	private String regionsTag;

	public RegionParser(Element root, String regionsTag) {
		super(root);
		this.regionsTag = regionsTag;
		this.regions = RegionUtils.parseRegions(this.element, this.regionsTag);
	}

	public HashMap<String, Region> getRegions() {
		return this.regions;
	}

}
