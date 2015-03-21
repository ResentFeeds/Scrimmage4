package me.skylertyler.scrimmage.team;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.TeamModule;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.TeamUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TeamHandler {

	/**
	 * class for handling with teams most likely adding
	 */

	public TeamHandler() {
	}

	public void addParticpatingMember(Team team, Player player) {
		for (Team teams : ((TeamModule) Scrimmage.getScrimmageInstance()
				.getLoader().getModuleContainer().getModule(TeamModule.class))
				.getTeams()) {
			teams.getMembers().remove(player.getName());
		}

		team.getMembers().add(player.getName());
		player.setPlayerListName(team.getColor() + player.getName());
		player.sendMessage(ChatColor.GRAY + "You joined the " + team.getColor()
				+ team.getName());
	}

	public Team teamForPlayer(Player player) {
		Team result = null;
		Team obs = TeamUtils.getObservers();
		for (Team team : TeamUtils.getParticpatingTeams()) {
			if (team != null) {
				if (team.isParticipatingMember(player)) {
					result = team;
				} else if (obs.isObserving(player)) {
					result = obs;
				}
			} else {
				// do something
				Log.logWarning("wtf ? the team is null!");
			}
		}
		return result;
	}
}
