package me.skylertyler.scrimmage.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.AdminChannel;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.channels.GlobalChannel;
import me.skylertyler.scrimmage.channels.TeamChannel;

public class ChannelUtils {

	// new global channel
	public static GlobalChannel newGlobalChannel() {
		return new GlobalChannel(ChatColor.LIGHT_PURPLE + "*", true,
				ChatColor.GOLD, "Global");
	}

	// new team channel
	public static TeamChannel newTeamChannel() {
		return new TeamChannel(ChatColor.LIGHT_PURPLE + "*", false,
				null, "Team");
	}

	// new admin channel
	public static AdminChannel newAdminChannel() {
		return new AdminChannel(ChatColor.LIGHT_PURPLE + "*", false,
				ChatColor.DARK_RED, "Admin");
	}

	public static Channel getChannel(Player player) {
		Channel result = null;
		for (Channel channel : Scrimmage.getScrimmageInstance().getMatch()
				.getChannels()) {
			if (channel.hasPlayer(player)) {
				result = channel;
			}
		}
		return result;
	}

	public static Channel getChannelByName(String channelName) {
		Channel result = null;
		for (Channel channel : Scrimmage.getScrimmageInstance().getMatch()
				.getChannels()) {
			if (channel.getChannelName().equalsIgnoreCase(channelName)) {
				result = channel;
			}
		}
		return result;
	}
	 

	public static GlobalChannel getGlobalChannel() {
		return (GlobalChannel) getChannelByName("Global");
	}

	public static TeamChannel getTeamChannel() {
		return (TeamChannel) getChannelByName("Team");
	}

	public static AdminChannel getAdminChannel() {
		return (AdminChannel) getChannelByName("Admin");
	}
}
