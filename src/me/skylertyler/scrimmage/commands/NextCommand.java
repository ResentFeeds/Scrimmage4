package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.match.Match;

import static org.bukkit.ChatColor.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NextCommand implements CommandExecutor {

	private Match match;

	public NextCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("next")) {
				Match match = this.getMatch();
				if (match.hasNext()) {
					player.sendMessage(match.getNext().getNextMapDescription());
				} else {
					player.sendMessage(WHITE + "There is no map set do "
							+ DARK_RED + " /setnext <map> " + WHITE + " first");
				}
			}
		} else {
			sender.sendMessage(RED + "You need to be a player!");
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}
}
