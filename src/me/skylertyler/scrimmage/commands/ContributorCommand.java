package me.skylertyler.scrimmage.commands;

import static org.bukkit.ChatColor.RED;

import java.util.Map.Entry;

import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ContributorCommand implements CommandExecutor {

	protected Match match;

	public ContributorCommand(Match match) {
		this.match = match;
	}

	// this is just for now
	// GRAY > * WHITE > contributor (contribution) if it has a contribution!
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("contributors")) {

				if (args.length > 0) {
					player.sendMessage(RED + "Too many arguments!");
					return false;
				}
				if (args.length == 0) {
					String format = null;
					MapInfo info = getMatch().getMap().getInfo();
					for (Entry<String, String> contributor : info.getContributorNames()
							.entrySet()) {
						String name = contributor.getKey();
						String contrib = contributor.getValue();

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
