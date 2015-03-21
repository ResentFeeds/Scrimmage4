package me.skylertyler.scrimmage.map;

import java.util.List;

import me.skylertyler.scrimmage.author.Author;
import me.skylertyler.scrimmage.contributor.Contributor;
import me.skylertyler.scrimmage.rules.Rule;
import me.skylertyler.scrimmage.version.Version;

public class MapInfo {

	protected String name;
	protected Version version;
	protected List<Author> authors;
	protected List<Contributor> contributors;
	
	protected String objective;
	// this will get updated every time they update to a newer proto version!
	protected Version proto = new Version(1, 3, 6);
	protected List<Rule> rules;

	public MapInfo(Version proto, String name, Version version, List<Author> authors,
			List<Contributor> contributors, List<Rule> rules, String objective) {
		this.proto = proto;
		this.name = name;
		this.version = version;
		this.authors = authors;
		this.contributors = contributors;
		this.rules = rules;
		this.objective = objective;
	}

	public String getName() {
		return this.name;
	}

	public Version getVersion() {
		return this.version;
	}

	public List<Contributor> getContributors() {
		return this.contributors;
	}

	public List<Author> getAuthors() {
		return this.authors;
	}

	public String getObjective() {
		return this.objective;
	}

	public Version getProto() {
		return this.proto;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public void setProto(Version proto) {
		this.proto = proto;
	}

	public List<Rule> getRules() {
		return this.rules;
	}

	public boolean hasContributors() {
		return this.contributors != null || this.contributors.size() > 0;
	}
}
