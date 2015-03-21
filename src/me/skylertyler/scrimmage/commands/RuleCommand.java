package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.rules.Rule;

import org.bukkit.ChatColor;
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
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			Map map = match.getMap();
			MapInfo info = map.getInfo();
			if (cmd.getName().equalsIgnoreCase("rules")) {
				if (match != null) {
					int i = 1;
					for (Rule rule : info.getRules()) {
						if (rule != null) {
							String format = i + ") " + rule.getRule();
							player.sendMessage(format);
							i++;
						} else {
							player.sendMessage(ChatColor.RED +"There are no rules for this map!");
						}
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
