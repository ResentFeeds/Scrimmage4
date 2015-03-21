package me.skylertyler.scrimmage.team;

public enum TeamType {

	Observing(0), Participating(1);

	protected final int id;

	TeamType(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
