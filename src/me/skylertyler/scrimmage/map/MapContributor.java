package me.skylertyler.scrimmage.map;

import java.util.HashMap;

public class MapContributor {

	protected HashMap<String, String> contributors;

	public MapContributor() {
		this.contributors = new HashMap<String, String>();
	}

	// gets the contributors for the map!
	public HashMap<String, String> getContributors() {
		return this.contributors;
	}

	public void addContributor(String input, String value) {
		this.contributors.put(input, value);
	}
}
