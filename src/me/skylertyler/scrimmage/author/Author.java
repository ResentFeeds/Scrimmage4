package me.skylertyler.scrimmage.author;

import java.util.UUID;

public class Author {

	private final String contribution;
	private final UUID uuid; 

	public Author(String contribution, UUID uuid) {
		this.contribution = contribution;
		this.uuid = uuid;
	}
	
	
	public Author(UUID uuid){
		this.contribution = null;
		this.uuid = uuid;
	}

	public String getContribution() {
		return this.contribution;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public boolean hasContribution() {
		return this.contribution != null;
	}
}
