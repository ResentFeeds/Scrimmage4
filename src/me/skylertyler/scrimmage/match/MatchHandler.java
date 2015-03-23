package me.skylertyler.scrimmage.match;

public class MatchHandler {

	protected Match current;

	public MatchHandler(Match current) {
		this.current = current;
	}

	public Match getCurrentMatch() {
		return this.current;
	}
}
