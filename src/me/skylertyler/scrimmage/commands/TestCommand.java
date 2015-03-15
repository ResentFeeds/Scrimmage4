package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.map.MapRules;
import me.skylertyler.scrimmage.match.Match;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

	protected Match match;

	public TestCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("test")) {
				Match match = this.getMatch();
				if (match != null) {
					Map map = match.getMap();
					MapInfo info = map.getInfo();
					MapRules rules = info.getRules();
					String format = null;
					ChatColor aqua = ChatColor.AQUA;
					int i = 1;
					for (String rule : rules.getRules()) {
						format = "" + aqua + i + ") " + rule;
						player.sendMessage(format);
					}

					i++;
				}else{
					player.sendMessage("format");
				}
			}
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}

}
