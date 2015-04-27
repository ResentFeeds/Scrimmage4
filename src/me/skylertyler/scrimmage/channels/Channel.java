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

	/**
	 * 
	 * @param prefix
	 *            the prefix (may change)
	 * @param bold
	 *            allow text being bold
	 * @param color
	 *            the color of the text
	 * @param channelName
	 *            the name of the channel
	 */
	public Channel(String prefix, @Nullable boolean bold,
			@Nullable ChatColor color, String channelName) {
		this.players = new ArrayList<>();
		this.prefix = prefix;
		this.bold = Optional.fromNullable(bold);
		this.color = color;
		this.channelName = channelName;
	}

	/** if it is bold */
	public boolean isBold() {
		return getBold() != false ? true : false;
	}

	/** checks if it is bold */
	// if true it will be bold */
	public boolean getBold() {
		return this.bold.isPresent() && this.bold.get() != false;
	}

	/** the color */
	public ChatColor getColor() {
		return this.color != null ? this.color : ChatColor.AQUA;
	}

	/** the channel name witbout "Channel" at the end */
	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * 
	 * @return the channel name
	 * 
	 */
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
	 * @returnm all the players in the channel
	 * 
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
