package me.skylertyler.scrimmage.event;

import me.skylertyler.scrimmage.match.Match;

import org.bukkit.event.Event;

public abstract class MatchEvent extends Event {

	private Match match;

	public MatchEvent(Match match) {
		super();
		this.match = match;
	}

	/** Gets the match that this event occurs in. */
	public Match getMatch() {
		return this.match;
	}
}
