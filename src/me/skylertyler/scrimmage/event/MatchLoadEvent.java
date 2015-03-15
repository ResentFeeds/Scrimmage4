package me.skylertyler.scrimmage.event;

import org.bukkit.event.HandlerList;

import me.skylertyler.scrimmage.match.Match;

public class MatchLoadEvent extends MatchEvent {

	private static HandlerList handlers = new HandlerList();

	public MatchLoadEvent(Match match) {
		super(match);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandler() {
		return handlers;
	}

}
