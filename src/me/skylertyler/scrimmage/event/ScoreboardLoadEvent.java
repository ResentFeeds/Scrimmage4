package me.skylertyler.scrimmage.event;

import me.skylertyler.scrimmage.match.Match;

import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardLoadEvent extends ScoreboardEvent {

	private static final HandlerList handlers = new HandlerList();
	private final Match match;

	public ScoreboardLoadEvent(Match match, Scoreboard board) {
		super(board);
		this.match = match;
	}

	public Match getMatch() {
		return this.match;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
