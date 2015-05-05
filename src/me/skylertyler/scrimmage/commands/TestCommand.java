package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.filter.Filter;
import me.skylertyler.scrimmage.filter.types.TeamFilter;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.MessageUtils;
import me.skylertyler.scrimmage.utils.XMLUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	private final Match match;

	public TestCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Filter filter = XMLUtils.getFilter(args[0]);
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			if (label.equalsIgnoreCase("filters")) {
				if (match != null) {
					Team team = match.getTeamHandler().teamForPlayer(player);
					if (filter != null) {
						if (filter instanceof TeamFilter) {
							TeamFilter teamFilter = (TeamFilter) filter;
							if (teamFilter.evaluate(team) != null) {
								player.sendMessage("team filter id "
										+ teamFilter.getTeam().getId() + " "
										+ "and your id is " + team.getId());
							}
						}
					} else {
						MessageUtils.warningMessage(player, ChatColor.RED
								+ "There is no filter by the id of ' "
								+ ChatColor.DARK_RED + args[0] + ChatColor.RED
								+ "'");
					}
				}
			}
		} else {
			if (filter instanceof TeamFilter) {
				TeamFilter team = (TeamFilter) filter;
				sender.sendMessage(team.getTeam().getId() + " is the team id!");
			}
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}

}
