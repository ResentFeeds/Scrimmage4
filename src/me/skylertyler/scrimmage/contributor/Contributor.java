package me.skylertyler.scrimmage.contributor;

import java.util.UUID;

public class Contributor {

	private final String contribution;
	private String contributor;
	private UUID uuid;

	public Contributor(String contribution, String contributor) {
		this.contribution = contribution;
		this.contributor = contributor;
	}

	public Contributor(String contributor) {
		this(null, contributor);
	}

	// TODO make this 
	public Contributor(String contribution, UUID uuid) {
		this.uuid = uuid;
		this.contribution = contribution;
	}

	public Contributor(UUID uuid) {
		this(null, uuid);
	}

	public String getContributor() {
		return this.contributor;
	}

	public String getContribution() {
		return this.contribution;
	}

	public boolean hasContribution() {
		return this.contribution != null;
	}

	public UUID getUUID() {
		return this.uuid;
	}
}
