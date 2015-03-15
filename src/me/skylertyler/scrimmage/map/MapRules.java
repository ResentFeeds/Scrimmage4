package me.skylertyler.scrimmage.map;

import java.util.ArrayList;
import java.util.List;
 

public class MapRules {

	protected List<String> rules;

	public MapRules() {
		this.rules = new ArrayList<>();
	}

	public List<String> getRules() {
		return this.rules;
	}

	public void addRule(String rule) {
		this.rules.add(rule);
	}
}
