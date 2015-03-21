package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.rules.Rule;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuleCommand implements CommandExecutor {

	protected Match match;

	public RuleCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			Map map = match.getMap();
			MapInfo info = map.getInfo();
			if (label.equalsIgnoreCase("rules")) {
				if (match != null) {
					int i = 1;
					for (Rule rule : info.getRules()) {
						String format = +i + ") " + rule.getRule();
						player.sendMessage(format);
						i++;
					}
				}
			}
		}
		return true;
	}

	public Match getMatch() {
		return this.match;
	}
}
