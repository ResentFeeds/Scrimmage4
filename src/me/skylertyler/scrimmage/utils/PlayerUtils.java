package me.skylertyler.scrimmage.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtils {

	/**
	 * 
	 * @param player
	 *            the player who is going to show
	 */
	public static void showPlayer(Player player) {
		/** get all the players in the server */
		for (Player players : Bukkit.getOnlinePlayers()) {
			/** show the player to the players */
			players.showPlayer(player);
		}
	}

	/**
	 * 
	 * @param player
	 *            the player who is going to hide
	 */
	public static void hidePlayer(Player player) {
		/** get all the players in the server */
		for (Player players : Bukkit.getOnlinePlayers()) {
			/** hide the player from the players */
			players.hidePlayer(player);
		}
	}
}
