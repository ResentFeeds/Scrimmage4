package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.event.TeamChangeEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.TeamUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

	protected Match match;

	public JoinCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("join")) {
				Match match = this.getMatch();
				if (match != null) {
					if (args.length < 1) {
						player.sendMessage(ChatColor.RED + "/join <team>");
						return false;
					}

					if (args.length > 1) {
						player.sendMessage(ChatColor.RED + "/join <team>");
						return false;
					}

					Team playerTeam = match.getTeamHandler().teamForPlayer(
							player);
					if (args.length == 1) {
						Team team = TeamUtils.getTeamByName(args[0]);
						TeamChangeEvent teamcEvent = new TeamChangeEvent(
								player, playerTeam, team, args[0]);
						this.match.getPluginManager().callEvent(teamcEvent);
					}
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED
					+ "You need to be a player to join a team!");
		}
		return true;
	}

	public Match getMatch() {
		return this.match;
	}
}
