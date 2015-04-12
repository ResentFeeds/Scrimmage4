package me.skylertyler.scrimmage.event;

import me.skylertyler.scrimmage.map.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import static org.bukkit.ChatColor.*;

public class MapsAreIdenticalEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private final Map m;
	private final Player p;
	private final String message;

	/**
	 * 
	 * @param player
	 *            the player
	 * @param map
	 */
	public MapsAreIdenticalEvent(Player player, Map map) {
		this.p = player;
		this.m = map;
		this.message = DARK_PURPLE + "The map " + GOLD
				+ map.getInfo().getName() + DARK_PURPLE
				+ " has already been set to be next";
	}

	public Player getPlayer() {
		return this.p;
	}

	public Map getMap() {
		return this.m;
	}


	public String getMessage() {
		return this.message;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
