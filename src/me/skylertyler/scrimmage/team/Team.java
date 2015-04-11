package me.skylertyler.scrimmage.team;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {
	// TODO spawn module and region module
	// TODO team adding and removing players (hiding and showing)

	// the teams name
	private final String name;
	// Unique identifier used to reference teams from other places in the XML.
	private final String id;
	// teams color
	private final ChatColor color;
	// max amount of players to a point!
	private final int max;

	// even though there is not going to be donators (duh its a scrimmage
	// plugin)
	// total amount of players even for donators!
	private final int overfill;

	// members

	protected List<String> members = new ArrayList<String>();
	// TEAMTYPE Observing or participating
	private final TeamType type;

	public Team(String name, String id, ChatColor color, int max, int overfill,
			TeamType observing) {
		this.name = name;
		this.id = id;
		this.color = color;
		this.max = max;
		this.overfill = overfill;
		this.type = observing;
	}

	public Team(String name, String id, ChatColor color, int max,
			TeamType participating) {
		this.name = name;
		this.id = id;
		this.color = color;
		this.max = max;
		this.overfill = max + 20;
		this.type = participating;
	}

	// getters

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public ChatColor getColor() {
		return this.color;
	}

	public int getMax() {
		return this.max;
	}

	public int getOverfill() {
		return this.overfill;
	}

	public boolean hasOverfill() {
		return this.overfill != 0;
	}

	public List<String> getMembers() {
		return this.members;
	}

	public boolean containsPlayer(Player player) {
		List<String> members = this.getMembers();
		return members.contains(player.getName()) ? true : false;
	} 

	public TeamType getType() {
		return this.type;
	}
}
