package me.skylertyler.scrimmage.event;


import me.skylertyler.scrimmage.map.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SetNextEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Player p;
	private final Map m;

	public SetNextEvent(Player player, Map map) {
		this.p = player;
		this.m = map;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public Player getPlayer() {
		return this.p;
	}

	public Map getMap() {
		return this.m;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
