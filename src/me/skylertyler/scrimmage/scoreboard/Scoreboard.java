package me.skylertyler.scrimmage.scoreboard;

public class Scoreboard {

	private final org.bukkit.scoreboard.Scoreboard board;

	public Scoreboard(org.bukkit.scoreboard.Scoreboard board) {
		this.board = board;
	}

	public org.bukkit.scoreboard.Scoreboard getNewBoard() {
		return this.board;
	}

}
