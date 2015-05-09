package me.skylertyler.scrimmage.scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import me.skylertyler.scrimmage.Scrimmage;

public class Scoreboard {

	/** the amount of total stuff on the scoreboard not counting the DisplayName */
	private int amount = 1;
	/** the new board */
	private org.bukkit.scoreboard.Scoreboard board;
	/** check if the scoreboard is enabled */
	private boolean enabled;

	public Scoreboard(boolean enabled){
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
	/**
	 * 
	 * @param name
	 *            the team name
	 * @param prefix
	 *            the prefixx
	 * @param suffix
	 *            the suffix
	 */
	public void registerNewTeam(String name, String prefix, String suffix) {
		Team team = this.board.registerNewTeam(name);
		team.setPrefix(prefix);
		team.setSuffix(suffix);
	}

	/** register a team with no prefix or suffix */
	/**
	 * 
	 * @param name
	 *            the team namn
	 */
	public void registerNewTeam(String name) {
		this.registerNewTeam(name, "", "");
	}

	/** setting the board for the player */
	public void setPlayerBoard(Player player) {
		player.setScoreboard(this.board);
	}

	public boolean isEnabled() {
		return this.enabled;
	}
}
