package me.skylertyler.scrimmage.exception;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

public class TeamNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(String team, @Nullable Player player){
		super(team + " isn't a team!");
		// will send the message if the player isnt null
		if(player != null){
			player.sendMessage(this.getMessage());
		}
	}
}
