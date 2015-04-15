package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.TeamUtils;

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
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			Map map = match.getMap();
			if (label.equalsIgnoreCase("test")) {
				if (match != null) {
					for (Team teams : TeamUtils.getTeamModule().getTeamParser()
							.getTeams()) {
						player.sendMessage(teams.getColor() + teams.getName());
					}
				}
			}
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}

}
