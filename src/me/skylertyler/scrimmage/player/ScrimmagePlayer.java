package me.skylertyler.scrimmage.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;

public class ScrimmagePlayer {

	private static List<ScrimmagePlayer> players = new ArrayList<ScrimmagePlayer>();
	private final Player player;

	public ScrimmagePlayer(Player player) {
		this.player = Preconditions.checkNotNull(player,
				"the player cant be null");
		getPlayers().add(this);
	}

	public Player getPlayer() {
		return this.player;
	}

	public static ScrimmagePlayer getPlayer(Player player) {
		for (ScrimmagePlayer players : getPlayers()) {
			if (players.getPlayer().equals(player)) {
				return players;
			}
		}
		return null;
	}

	public static List<ScrimmagePlayer> getPlayers() {
		return players;
	}
}
