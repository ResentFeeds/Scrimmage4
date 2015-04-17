package me.skylertyler.scrimmage.author;

import java.util.UUID;

public class Author {
	private final String contribution;
	private final UUID uuid;

	public Author(UUID uuid, String contribution) {
		this.uuid = uuid;
		this.contribution = contribution;
	}

	public Author(UUID uuid) {
		this(uuid, null);
	}

	public String getContribution() {
		return this.contribution;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public boolean hasContribution() {
		return this.getContribution() != null;
	}
}
