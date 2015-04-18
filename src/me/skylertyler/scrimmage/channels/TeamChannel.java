package me.skylertyler.scrimmage.channels;

import me.skylertyler.scrimmage.team.Team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class TeamChannel extends Channel {

	public TeamChannel(String prefix, boolean bold, ChatColor color,
			String channelName) {
		super(prefix, bold, color, channelName);
	}

	@Override
	public String format(Team team, Player player, String message) {
		return team.getColor() + "[Team] " + WHITE + "<"  + getPrefix() + team.getColor()
				+ player.getDisplayName() + WHITE + ">: " + message;
	}
}
