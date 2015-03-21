package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.team.TeamHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MyTeamCommand implements CommandExecutor {

	protected Match match;

	public MyTeamCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("myteam")) {
				Match match = this.getMatch();
				if (match != null) {
					TeamHandler handler = match.getTeamHandler();
					Team team = handler.teamForPlayer(player);
					if (team == null) {
						player.sendMessage(ChatColor.RED
								+ "Your not on a team!");
						return false;
					}

					player.sendMessage(ChatColor.GRAY + "Your on the "
							+ team.getColor() + team.getName());
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED
					+ "You need to be a player to check what team your on!");
		}

		return true;
	}

	public Match getMatch() {
		return this.match;
	}

}
