package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.utils.ChannelUtils;

import static org.bukkit.ChatColor.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class ACommand implements CommandExecutor {

	public boolean onCommand(org.bukkit.command.CommandSender sender,
			Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("a")) {
				Channel channel = ChannelUtils.getChannel(player);
				if (player.isOp() || player.hasPermission("channel.admin.join")) {
					if (channel == null) {
						ChannelUtils.getAdminChannel().addPlayer(player);
					} else {
						if (channel != null) {
							channel.removePlayer(player);
							ChannelUtils.getAdminChannel().addPlayer(player);
						}
					}
				} else {
					player.sendMessage(RED
							+ "You need to be op in order to join the ");
				}
			}
		}
		return false;

	}

}
