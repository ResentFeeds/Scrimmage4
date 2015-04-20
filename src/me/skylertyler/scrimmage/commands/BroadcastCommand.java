package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("broadcast")) {
				if (args.length <= 0) {
					player.sendMessage(RED + "Not enough arguments!");
					return false;
				}
				String msg = MessageUtils.broadcastMessage(args);
				Bukkit.broadcastMessage(msg);
			}
		} else {
			// allow broadcasting from the console
			if (args.length == 0) {
				sender.sendMessage(RED + "Not enough arguments!");
				return false;
			}
			String msg = MessageUtils.broadcastMessage(args);
			Bukkit.broadcastMessage(msg);
		}

		return false;
	}
}
