package me.skylertyler.scrimmage.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.utils.ChannelUtils;

public class TCommand implements CommandExecutor {

	private Channel channel;

	public TCommand() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("t")) {
				if (args.length == 0) {
					this.channel = ChannelUtils.getChannel(player);

					boolean nul = this.channel == null;
					if (nul) {
						ChannelUtils.getTeamChannel().addPlayer(player);
					} else {
						if (this.channel != ChannelUtils.getTeamChannel()) {
							this.channel.removePlayer(player);
							ChannelUtils.getTeamChannel().addPlayer(player);
						} else {
							player.sendMessage(RED + "You are already in the "
									+ DARK_RED + this.channel.getChannelName()
									+ RED + " Channel");
							return false;
						}
					}
				}

				if (args.length > 1) {
					player.sendMessage(RED + "Too many arguments!");
					return false;
				}

				if (args.length == 1) {
					// TODO ..

				}
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to join a Channel");
		}
		return false;
	}
}