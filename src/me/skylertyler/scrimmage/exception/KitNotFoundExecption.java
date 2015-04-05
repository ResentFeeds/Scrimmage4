package me.skylertyler.scrimmage.exception;

import javax.annotation.Nullable;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;

public class KitNotFoundExecption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String name;
	private final Player player;
	private String message;

	public KitNotFoundExecption(String name, @Nullable  Player player) { 
		Preconditions.checkNotNull(player, "player");
		this.name = name;
		this.player = player;
		this.message = ChatColor.RED + "there is no kit by the name of " +  ChatColor.DARK_RED + getName();
		if(this.player != null){
			this.player.sendMessage(getMessage());
		}
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
