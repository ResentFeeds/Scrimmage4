package me.skylertyler.scrimmage.channels;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class Channel {

	private final List<Player> players;
	private final String channelName;

	public Channel(String channelName) {
		this.players = new ArrayList<>();
		this.channelName = channelName;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
		player.sendMessage("Your channel is now " + AQUA
				+ this.getChannelName() + " Channel");
	}

	public void removePlayer(Player player) {
		this.players.remove(player);
	}

	public boolean hasPlayer(Player player) {
		if (this.getPlayers().contains(player)) {
			return true;
		}
		return false;
	}
}
