package me.skylertyler.scrimmage.channels;

import me.skylertyler.scrimmage.team.Team;

import static org.bukkit.ChatColor.*;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GlobalChannel extends Channel {

	public GlobalChannel(String prefix, boolean bold, ChatColor color,
			String channelName) {
		super(prefix, bold, color, channelName);
	}

	 
	@Override
	public String format(Team team, Player player, String message) {
		return "<" + getPrefix() + team.getColor() + player.getDisplayName()
				+ WHITE + ">: " + message;
	}
}
