package me.skylertyler.scrimmage.event;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;

public abstract class ScoreboardEvent extends Event {

	private final Scoreboard board;

	public ScoreboardEvent(Scoreboard board) {
		this.board = board;
	}

	public Scoreboard getBoard() {
		return this.board;
	}
}
