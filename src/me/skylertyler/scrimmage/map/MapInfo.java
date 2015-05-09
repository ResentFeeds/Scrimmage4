package me.skylertyler.scrimmage.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.skylertyler.scrimmage.author.Author;
import me.skylertyler.scrimmage.contributor.Contributor;
import me.skylertyler.scrimmage.rules.Rule;
import me.skylertyler.scrimmage.utils.UUIDUtils;
import me.skylertyler.scrimmage.version.Version;

import org.bukkit.entity.Player;

public class MapInfo {

	private final String name;
	/** the name for the map */
	private final Version version;
	/** the map version */
	private final List<Author> authors;
	/** major authors of the map */
	private final List<Contributor> contributors;
	/** a list of contributors for the map */
	private final String objective;
	/** a map objective */
	// this will get updated every time they update to a newer proto version!
	private final Version proto;
	/** map protocal version */
	private final List<Rule> rules;
	/** the rules for the map */
	private HashMap<String, String> authorNames;
	/** used to get the author names from the uuid with the contribution */
	private HashMap<String, String> contributorNames;
	private List<String> mapRules;
	/** used for current maps rules page not the parsing for the rules */
	private final boolean internal;

	/** check if its internal */

	public MapInfo(Version proto, boolean internal, String name,
			String objective, Version version, List<Author> authors,
			List<Contributor> contributors, List<Rule> rules) {
		this.authorNames = new HashMap<>();
		this.contributorNames = new HashMap<>();
		this.mapRules = new ArrayList<>();
		this.proto = proto;
		this.internal = internal;
		this.name = name;
		this.objective = objective;
		this.version = version;
		this.authors = authors;
		this.contributors = contributors;
		this.rules = rules;

		/**
		 * used for reloading and clearing for authors, contributors, etc
		 */

		/**
		 * NOTE: You need to wait until the match start to reload (if needed)
		 * ....
		 */

		// KNOWN BUG: when you reload before the match starts it will make the
		// authors "null".
		addAuthorNames();
		addContributorNames();
		addMapRules();
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
		return getContributors() != null && getContributors().size() > 0;
	}

	public void addAuthorNames() {
		clearAuthorNames();
		for (Author author : getAuthors()) {
			String name = UUIDUtils.getNameByUUID(author.getUUID());
			String contribution = author.getContribution();
			authorNames.put(name, contribution);
		}
	}

	public void addContributorNames() {
		clearContributorNames();
		for (Contributor contributor : getContributors()) {
			String name = UUIDUtils.getNameByUUID(contributor.getUUID());
			String contribution = contributor.getContribution();
			contributorNames.put(name, contribution);
		}
	}

	public void clearContributorNames() {
		getContributorNames().clear();
	}

	public HashMap<String, String> getAuthorNames() {
		return this.authorNames;
	}

	public HashMap<String, String> getContributorNames() {
		return this.contributorNames;
	}

	public void clearAuthorNames() {
		getAuthorNames().clear();
	}

	public boolean getInternal() {
		return this.internal;
	}

	public boolean isInternal() {
		return this.getInternal() != false ? true : false;
	}

	public boolean hasRules() {
		return getRules() != null || getRules().size() > 0;
	}

	public void addMapRules() {
		clearMapRules();
		for (Rule rule : this.getRules()) {
			mapRules.add(rule.getRule());
		}
	}

	public void clearMapRules() {
		this.getMapRules().clear();
	}

	public List<String> getMapRules() {
		return this.mapRules;
	}

	/**
	 * 
	 * @param rule
	 *            get a rule by a string
	 * @return a rule (used for the rules page)
	 */
	public Rule getRuleByString(String rule) {
		Rule r = null;
		for (Rule rules : this.getRules()) {
			if (rules.getRule().equalsIgnoreCase(rule)) {
				r = rules;
			}
		}
		return r;
	}

	public boolean isAuthor(Player player) {
		for (Author author : getAuthors()) {
			if (author.getUUID().equals(player.getUniqueId())) {
				return true;
			}
		}
		return false;
	}

}
