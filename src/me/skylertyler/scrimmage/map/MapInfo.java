package me.skylertyler.scrimmage.map;

import java.util.HashMap;
import java.util.List;

import me.skylertyler.scrimmage.author.Author;
import me.skylertyler.scrimmage.contributor.Contributor;
import me.skylertyler.scrimmage.rules.Rule;
import me.skylertyler.scrimmage.utils.UUIDUtils;
import me.skylertyler.scrimmage.version.Version;

public class MapInfo {

	private final String name;
	private final Version version;
	private final List<Author> authors;
	private final List<Contributor> contributors;
	private final String objective;
	// this will get updated every time they update to a newer proto version!
	private final Version proto;
	private final List<Rule> rules;
	private HashMap<String, String> authorNames;

	public MapInfo(Version proto, String name, Version version,
			List<Author> authors, List<Contributor> contributors,
			List<Rule> rules, String objective) {
		this.proto = proto;
		this.name = name;
		this.version = version;
		this.authors = authors;
		this.contributors = contributors;
		this.rules = rules;
		this.objective = objective;
		this.authorNames = new HashMap<>();
		addAuthorNames();
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

	public List<Rule> getRules() {
		return this.rules;
	}

	public boolean hasContributors() {
		return getContributors() != null || getContributors().size() > 0;
	}

	public void addAuthorNames() {
		authorNames.clear();
		for (Author author : getAuthors()) {
			String name = UUIDUtils.getNameByUUID(author.getUUID());
			String contribution = author.getContribution();
			authorNames.put(name, contribution);
		}
	}

	public HashMap<String, String> getAuthorNames() {
		return this.authorNames;
	}

}
