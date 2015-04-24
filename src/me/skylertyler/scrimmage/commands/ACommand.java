package me.skylertyler.scrimmage.commands;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.AdminChannel;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.event.ChannelChangeEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.utils.ChannelUtils;
import me.skylertyler.scrimmage.utils.MessageUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class ACommand implements CommandExecutor {

	private Match match;

	public ACommand() {
		this.match = Scrimmage.getScrimmageInstance().getMatch();
	}

	public boolean onCommand(org.bukkit.command.CommandSender sender,
			Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("a")) {
				Channel channel = ChannelUtils.getChannel(player);
				if (args.length == 0) {
					/**
					 * the player needs to be op , or they need the permssion
					 * "channel.admin.join" to join the Admin Channel
					 */
					if (player.isOp()
							|| player.hasPermission("channel.admin.join")) {
						ChannelChangeEvent event = new ChannelChangeEvent(
								player, channel, ChannelUtils.getAdminChannel());
						this.match.getPluginManager().callEvent(event);
					} else {
						player.sendMessage(RED
								+ "You need to be op in order to join the "
								+ ChannelUtils.getAdminChannel().getName());
					}
				}

				AdminChannel adminChannel = ChannelUtils.getAdminChannel();
				if (args.length >= 1) {
					// TODO FIX
					if (player.isOp()) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.isOp()
									|| p.hasPermission("channel.admin.recieve")) {
								p.sendMessage(adminChannel.format(
										this.match.getTeamHandler()
												.teamForPlayer(player), player,
										MessageUtils.broadcast(args)));
							}
						}
					} else {
						player.sendMessage(RED
								+ "You need to be op to send a message from the "
								+ adminChannel.getName());
					}
				}
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to join the admin channel");
		}
		return false;

	}

}
