package me.skylertyler.scrimmage.channels;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.team.Team;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.base.Optional;

public abstract class Channel {

	private final List<Player> players;
	private String channelName;
	private ChatColor color;
	private Optional<Boolean> bold;
	private String prefix;

	public Channel(String prefix, @Nullable boolean bold,
			@Nullable ChatColor color, String channelName) {
		this.players = new ArrayList<>();
		this.prefix = prefix;
		this.bold = Optional.fromNullable(bold);
		this.color = color;
		this.channelName = channelName;
	}

	public boolean isBold() {
		return getBold() != false ? true : false;
	}

	public boolean getBold() {
		return this.bold.isPresent() && this.bold.get() != false;
	}

	public ChatColor getColor() {
		return this.color != null ? this.color : ChatColor.AQUA;
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

	public String getPrefix() {
		return this.prefix;
	}

	public boolean hasPrefix() {
		return getPrefix() != "";
	}

	/** format the message */
	public abstract String format(Team team, Player player, String message);
}
