package me.skylertyler.scrimmage.event;

import me.skylertyler.scrimmage.match.Match;

import org.bukkit.event.HandlerList;

public class MatchStartEvent extends MatchEvent {

	private static final HandlerList handlers = new HandlerList();

	public MatchStartEvent(Match match) {
		super(match);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
