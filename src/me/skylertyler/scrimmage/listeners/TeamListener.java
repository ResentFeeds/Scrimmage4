package me.skylertyler.scrimmage.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import me.skylertyler.scrimmage.MatchListener;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.event.TeamChangeEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.team.TeamHandler;
import static org.bukkit.ChatColor.*;

public class TeamListener extends MatchListener {

	public TeamListener() {
		super(Scrimmage.getScrimmageInstance().getMatch());
	}

	@EventHandler
	public void onTeamChange(TeamChangeEvent event) {
		Match match = this.getMatch();
		Player player = event.getPlayer();
		boolean hasNewTeam = event.hasNewTeam();
		if (hasNewTeam) {
			Team playerTeam = event.getOldTeam();
			Team newTeam = event.getNewTeam();
			if (playerTeam == newTeam) {
				event.setCancelled(true);
				player.sendMessage(RED + "You are already in the "
						+ newTeam.getColor() + newTeam.getName());
			} else {
				/**
				 * add the player if the team isnt the same team that they are
				 * currently on
				 */
				TeamHandler teamHandler = match.getTeamHandler();
				teamHandler.addParticpatingMember(newTeam, player);
			}

		} else {

			/** just a little checking if they got args[0] */
			boolean args = event.hasArgsString();
			if (args) {
				String notFound = event.getArgsString();
				player.sendMessage(RED + "there is no team by the name of "
						+ DARK_RED + notFound);
			}
		}
	}
}
