package me.skylertyler.scrimmage.map;

public class MapInfo {

	protected String name;
	protected MapProto proto;
	protected MapVersion version;
	protected MapAuthor authors;
	protected MapContributor contributors;
	protected MapRules rules;
	protected String objective;
	protected MapProto currentMapProto = new MapProto(1, 3, 6);

	public MapInfo(String name, MapVersion version, MapAuthor authors,
			MapContributor contributors, String objective, MapRules rules) {
		this.name = name;
		this.version = version;
		this.authors = authors;
		this.contributors = contributors;
		this.objective = objective;
		this.rules = rules;
	}

	public String getName() {
		return name;
	}

	public MapVersion getVersion() {
		return version;
	}

	public MapAuthor getAuthors() {
		return authors;
	}

	public MapContributor getContributors() {
		return contributors;
	}

	public MapRules getRules() {
		return rules;
	}

	public String getObjective() {
		return objective;
	}
}
