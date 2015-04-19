package me.skylertyler.scrimmage;

import org.bukkit.event.Listener;

import me.skylertyler.scrimmage.match.Match;

public abstract class MatchListener implements Listener {

	protected final Match match;

	public MatchListener(Match match) {
		this.match = match;
	}

	public Match getMatch() {
		return this.match;
	}
}
