package me.skylertyler.scrimmage.commands;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.map.MapInfo;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.version.Version;

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
					MapInfo info = getMatch().getMap().getInfo();
					Version proto = info.getProto();
					player.sendMessage(proto.toString()
							+ " is the proto version for this map :)");
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
