package me.skylertyler.scrimmage.event;

import me.skylertyler.scrimmage.match.Match;

import org.bukkit.event.HandlerList;

public class MatchEndEvent extends MatchEvent {

	private static HandlerList handlers = new HandlerList();

	public MatchEndEvent(Match match) {
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
