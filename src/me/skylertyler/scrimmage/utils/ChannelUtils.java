package me.skylertyler.scrimmage.utils;

import org.bukkit.entity.Player;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.channels.GlobalChannel;
import me.skylertyler.scrimmage.channels.TeamChannel;

public class ChannelUtils {

	public static GlobalChannel newGlobalChannel() {
		return new GlobalChannel("Global");
	}

	public static TeamChannel newTeamChannel() {
		return new TeamChannel("Team");
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
}
