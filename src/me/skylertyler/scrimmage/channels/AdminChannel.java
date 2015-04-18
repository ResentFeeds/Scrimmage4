package me.skylertyler.scrimmage.channels;

import me.skylertyler.scrimmage.team.Team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class AdminChannel extends Channel {

	public AdminChannel(String prefix, boolean bold, ChatColor color,
			String channelName) {
		super(prefix, bold, color, channelName);
	}

	@Override
	public String format(Team team, Player player, String message) {
		String admin_prefix = GRAY + "[" + GOLD + "A" + GRAY + "]";
		String str = team != null ? admin_prefix + WHITE + " <" + getPrefix()
				+ team.getColor() + player.getDisplayName() + WHITE + ">: "
				+ message : admin_prefix + WHITE + "<" + getPrefix() + WHITE
				+ player.getDisplayName() + ">: " + message;
		return str;
	}
}
