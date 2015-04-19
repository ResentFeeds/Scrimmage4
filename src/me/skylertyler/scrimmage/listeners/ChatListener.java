package me.skylertyler.scrimmage.listeners;

import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.AdminChannel;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.channels.GlobalChannel;
import me.skylertyler.scrimmage.channels.TeamChannel;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.ChannelUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	private Match match;

	public ChatListener() {
		this.match = Scrimmage.getScrimmageInstance().getMatch();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChannelChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Team team = this.match.getTeamHandler().teamForPlayer(player);
		Channel channel = ChannelUtils.getChannel(player);
		String message = event.getMessage();
		// works
		if (channel instanceof TeamChannel) {
			TeamChannel teamChannel = (TeamChannel) channel;
			if (teamChannel != null) {
				List<String> teamMates = team.getMembers();
				for (String string : teamMates) {
					Player member = Bukkit.getPlayer(string);
					// try this;
					event.setCancelled(true);
					if (this.match.getMap().getInfo().isAuthor(player)) {
						event.setFormat(teamChannel.format(team, player,
								message));
					} else {
						event.setFormat(teamChannel.format(team, player,
								message));
					}
					member.sendMessage(event.getFormat());
				}
			}
		}

		if (channel instanceof GlobalChannel) {
			GlobalChannel global = (GlobalChannel) channel;
			if (global != null) {
				event.setCancelled(true);
				if (this.match.getMap().getInfo().isAuthor(player)) {
					this.match.broadcast(global.format(team, player, message));
				} else {
					this.match.broadcast(global.format(team, player, message));
				}
			}
		}

		if (channel instanceof AdminChannel) {
			AdminChannel admin = (AdminChannel) channel;
			if (admin != null) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					/**
					 * the player needs to be op. or have the permission
					 * 'admin.channel.receieve' to get the message
					 */
					if (players.isOp()
							|| players.hasPermission("admin.channel.recieve")) {
						event.setCancelled(true);
						/** check the player is a contributor or a author */
						if (this.match.getMap().getInfo().isAuthor(player)) {
							event.setFormat(admin.format(team, player, message));
						} else {
							/**
							 * do this if they are not a contributor or a author
							 */
							event.setFormat(admin.format(team, player, message));
						}
						players.sendMessage(event.getFormat());
					}
				}
			}
		}
	}
}
