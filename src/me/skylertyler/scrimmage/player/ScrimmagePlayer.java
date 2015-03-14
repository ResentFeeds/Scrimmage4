package me.skylertyler.scrimmage.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class ScrimmagePlayer {

	protected Player player;
	protected static List<ScrimmagePlayer> players = new ArrayList<ScrimmagePlayer>();

	public ScrimmagePlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return this.player;
	}

	public static ScrimmagePlayer getPlayer(Player player) {
		for (ScrimmagePlayer players : getPlayers()) {
			if (players.getPlayer() == player) {
				return players;
			}
		}
		return null;
	}

	public static List<ScrimmagePlayer> getPlayers() {
		return players;
	}
}
