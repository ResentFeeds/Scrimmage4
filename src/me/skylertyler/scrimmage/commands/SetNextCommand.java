package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.event.SetNextEvent;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.match.Match;
import static org.bukkit.ChatColor.*;

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
						player.sendMessage(RED + "Not enough arguments!");
						player.sendMessage(RED + "/setnext <map>");
						return false;
					}

					if (args.length > 1) {
						player.sendMessage(RED + "Too many arguments");
						player.sendMessage(RED + "/setnext <map>");
						return false;
					}

					if (args.length == 1) {
						Map map = match.getScrimmage().getLoader()
								.getMap(args[0]);
						if (map == null) {
							player.sendMessage(RED
									+ "There is no map by the name of '"
									+ DARK_RED + args[0] + RED + "'!");
							return false;
						}
						SetNextEvent event = new SetNextEvent(player, map);
						// call the event above :)
						match.getPluginManager().callEvent(event);
						match.setNext(map);
					}
				} else {
					player.sendMessage(RED + "wait until the match starts!");
				}
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to set the next map!");
		}
		return true;
	}

	public Match getMatch() {
		return this.match;
	}
}
