package me.skylertyler.scrimmage.exception;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitNotFoundExecption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;
	private final Player player;
	private String message;

	public KitNotFoundExecption(String name, Player player) { 
		this.name = name;
		this.player = player;
		this.message = ChatColor.RED + "there is no kit by the name of " +  ChatColor.DARK_RED + getName();
		player.sendMessage(getMessage());
	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}

	public String getMessage() {
		return message;
	}

}
