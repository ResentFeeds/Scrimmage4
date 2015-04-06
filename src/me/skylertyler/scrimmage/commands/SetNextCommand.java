package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.match.Match;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetNextCommand implements CommandExecutor {

	protected Match match;

	public SetNextCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Match match = this.getMatch();
			if (cmd.getName().equalsIgnoreCase("setnext")) {
				if (match != null) {
					if (args.length < 1) {
						player.sendMessage(ChatColor.RED
								+ "Not enough arguments!");
						player.sendMessage(ChatColor.RED + "/setnext <map>");
						return false;
					}

					if (args.length > 1) {
						player.sendMessage(ChatColor.RED + "Too many arguments");
						player.sendMessage(ChatColor.RED + "/setnext <map>");
						return false;
					}

					if (args.length == 1) {
						Map map = match.getScrimmage().getLoader()
								.getMap(args[0]);
						if (map == null) {
							player.sendMessage(ChatColor.RED
									+ "There is no map by the name of '"
									+ ChatColor.RED + args[0] + ChatColor.RED
									+ "'!");
							return false;
						}

						player.sendMessage(ChatColor.GRAY + "The map "
								+ ChatColor.GOLD + map.getInfo().getName()
								+ ChatColor.GRAY + " has been set to be next!");
						match.setNext(map);
					}
				} else {
					player.sendMessage(ChatColor.RED
							+ "wait until the match starts!");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED
					+ "You need to be a player to set the next map!");
		}
		return true;
	}

	public Match getMatch() {
		return this.match;
	}
}
