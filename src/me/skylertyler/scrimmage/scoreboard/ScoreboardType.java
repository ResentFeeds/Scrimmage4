package me.skylertyler.scrimmage.scoreboard;

import org.bukkit.ChatColor;

public enum ScoreboardType {
	// this wont work (until i fix the map loader parsing all the xml files at
	// once -_-)
	// all the scoreboard types
	Objectives(ChatColor.GOLD + "Objectives"), Wool(ChatColor.WHITE + "Wools"), Blitz(
			ChatColor.GOLD + "Blitz:"), Rage(ChatColor.GOLD + "Blitz: Rage"), Score(
			ChatColor.GOLD + "Scores");

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
