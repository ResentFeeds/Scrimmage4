package me.skylertyler.scrimmage.channels;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Channel {

	private final List<Player> players;
	private String channelName;
	private ChatColor color;
	private boolean bold;

	public Channel(@Nullable boolean bold, @Nullable ChatColor color,
			String channelName) {
		this.players = new ArrayList<>();
		this.bold = bold;
		this.color = color;
		this.channelName = channelName;

		if (this.color == null) {
			// default color ?
			this.color = ChatColor.AQUA;
		}
	}

	public boolean isBold() {
		return getBold() != false ? true : false;
	}

	public boolean getBold() {
		return this.bold;
	}

	public ChatColor getColor() {
		return this.color;
	}

	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * 
	 * @return get the name of the channel with the color :)
	 */
	/** if its bold it will get the bold first */
	public String getName() {
		String result = null;
		if (isBold()) {
			String bold = ChatColor.BOLD.toString();
			result = getColor() + bold + getChannelName();
		} else {
			result = getColor() + getChannelName();
		}
		return result + " Channel";
	}

	/**
	 * 
	 * @return may change this but get all the players in the channel... ? idk
	 *         why xD
	 */

	public List<Player> getPlayers() {
		return this.players;
	}

	public void addPlayer(Player player) {
		this.players.add(player); 
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
