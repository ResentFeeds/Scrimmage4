package me.skylertyler.scrimmage.commands;

import static org.bukkit.ChatColor.*;

import java.util.Map.Entry;

import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AuthorCommand implements CommandExecutor {

	private final Match match;

	public AuthorCommand(Match match) {
		this.match = match;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("authors")) {

				if (args.length > 0) {
					player.sendMessage(RED + "Too many arguments!");
					return false;
				}
				if (args.length == 0) {
					String format = null;
					MapInfo info = getMatch().getMap().getInfo();
					for (Entry<String, String> author : info.getAuthorNames()
							.entrySet()) {
						String name = author.getKey();
						String contrib = author.getValue();

						if (contrib != null) {
							format = name + "  " + contrib;
						} else {
							format = name;
						}

						String result = format;
						player.sendMessage(result);
					}
					return true;
				}
			}
		}
		return false;
	}

	public Match getMatch() {
		return this.match;
	}
}
