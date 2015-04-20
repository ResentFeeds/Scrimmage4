package me.skylertyler.scrimmage.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import me.skylertyler.scrimmage.Scrimmage;

public class Scoreboard {

	private int amount = 1;
	private org.bukkit.scoreboard.Scoreboard board;
	private boolean enabled;

	public Scoreboard(boolean enabled) {
		this.enabled = enabled;
		/** only do stuff if the scoreboard is enabled */
		if (isEnabled()) {
			this.board = Scrimmage.getScrimmageInstance().getServer()
					.getScoreboardManager().getNewScoreboard();
		}
	}

	public org.bukkit.scoreboard.Scoreboard getNewBoard() {
		return this.board;
	}

	public void registerNewObjective(String objective, DisplaySlot slot,
			String displayName) {
		Objective newObjective = this.board.registerNewObjective(objective
				+ "-" + this.amount, "dummy");
		newObjective.setDisplayName(displayName);
		newObjective.setDisplaySlot(slot);
		this.amount++;
	}

	/** registering a new team */
	public void registerNewTeam(String name, String prefix, String suffix) {
		Team team = this.board.registerNewTeam(name);
		team.setPrefix(prefix);
		team.setSuffix(suffix);
	}

	/** setting the board for the player */
	public void setPlayerBoard(Player player) {
		player.setScoreboard(this.board);
	}

	public boolean isEnabled() {
		return this.enabled;
	}
}
