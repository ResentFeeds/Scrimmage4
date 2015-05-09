package me.skylertyler.scrimmage.scoreboard;

import org.bukkit.ChatColor;

public enum ScoreboardType {
	Objectives(ChatColor.GOLD + "Objectives"), Wool(ChatColor.WHITE + "Wools"), Blitz(
			ChatColor.GOLD + "Blitz:"), Rage(ChatColor.GOLD + "Blitz: "), Score(
			ChatColor.GOLD + "Scores"), Flag(ChatColor.AQUA + "Score");

	// field for the displayName
	private final String displayName;

	// constructor
	ScoreboardType(String displayName) {
		this.displayName = displayName;
	}

	// getter method for the display name!
	public String getDisplayName() {
		return this.displayName;
	}
}
