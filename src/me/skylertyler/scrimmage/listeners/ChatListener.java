package me.skylertyler.scrimmage.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.channels.GlobalChannel;
import me.skylertyler.scrimmage.channels.TeamChannel;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.ChannelUtils;

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
		// NEED to test this with one more player....
		if (channel instanceof TeamChannel) {
			TeamChannel teamChannel = (TeamChannel) channel;
			if (teamChannel != null) {
				List<String> teamMates = team.getMembers();
				for (String string : teamMates) {
					Player member = Bukkit.getPlayer(string);
					// try this;
					event.setCancelled(true);
					event.setFormat(team.getColor() + "[Team] " + WHITE + "<"
							+ team.getColor() + player.getDisplayName() + WHITE
							+ ">: " + event.getMessage());
					member.sendMessage(event.getFormat());
				}
			}
			return;
		}

		if (channel instanceof GlobalChannel) {
			GlobalChannel global = (GlobalChannel) channel;
			if (global != null) {
				event.setCancelled(true);
				this.match.broadcast(WHITE + "<" + team.getColor()
						+ player.getDisplayName() + WHITE + ">: "
						+ event.getMessage());
			}
		}
	}
}
