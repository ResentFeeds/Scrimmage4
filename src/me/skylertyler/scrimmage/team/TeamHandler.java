package me.skylertyler.scrimmage.team;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.config.types.Config;
import me.skylertyler.scrimmage.kit.BookKit;
import me.skylertyler.scrimmage.modules.TeamModule;
import me.skylertyler.scrimmage.utils.PlayerUtils;
import me.skylertyler.scrimmage.utils.TeamUtils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class TeamHandler {

	/**
	 * class for handling with teams most likely adding and removing players
	 * 
	 */

	public TeamHandler() {
	}

	public void addParticpatingMember(Team team, Player player) {
		for (Team teams : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer().getModule(TeamModule.class))
				.getTeamParser().getTeams()) {
			teams.removeMember(player);
		}

		team.addMember(player);
		player.setPlayerListName(team.getColor() + player.getName());
		player.setDisplayName(team.getColor() + player.getName()
				+ ChatColor.RESET);
		// TODO make their overhead name be the teams color
		player.sendMessage(ChatColor.GRAY + "You joined the " + team.getColor()
				+ team.getName());

		player.getInventory().clear();

		/** if the team is observers */
		if (team == TeamUtils.getTeamByName("Observers")) {
			PlayerUtils.hidePlayer(player);
			player.setGameMode(GameMode.CREATIVE);
			Config config = Scrimmage.getScrimmageInstance().getConfigFile();
			/** only apply the book if the book is enabled */
			if (config.bookEnabled()) {
				BookKit obs_book = config.getObserversBook();
				obs_book.apply(player);
			}
			/** other wise if they are not observers do this below */
		} else {
			PlayerUtils.showPlayer(player);
			player.setGameMode(GameMode.SURVIVAL);
		}
		// SpawnUtils.spawn(player);
	}

	/** get the team that the player is currently on */
	public Team teamForPlayer(Player player) {
		Team result = null;
		Team obs = TeamUtils.getObservers();
		for (Team team : TeamUtils.getParticpatingTeams()) {
			/** check all the particpating teams */
			if (team.containsPlayer(player)) {
				result = team;
			} else {
				/** check the observers */
				if (obs.containsPlayer(player)) {
					result = obs;
				}
			}
		}
		return result;
	}
}
