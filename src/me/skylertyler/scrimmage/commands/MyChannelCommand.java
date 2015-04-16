package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.utils.ChannelUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class MyChannelCommand implements CommandExecutor {

	private Channel channel;

	public MyChannelCommand() {
		// nothing
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equals("mychannel")) {
				if (args.length > 1) {
					player.sendMessage(RED + "Too many arguments");
					return false;
				}

				if (args.length == 0) {
					this.channel = ChannelUtils.getChannel(player); 
					if (this.channel != null) {
						player.sendMessage("You are in the "
								+ this.channel.getName());
					} else {
						player.sendMessage(RED + "You are not in a channel yet");
					}
				}
			}
		}
		return false;
	}

}
