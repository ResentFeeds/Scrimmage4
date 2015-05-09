package me.skylertyler.scrimmage.contributor;

import java.util.UUID;

public class Contributor {

	private final String contribution;
	private UUID uuid;

	/** a contributor with a contribution and a UUID */
	public Contributor(UUID uuid, String contribution) {
		this.uuid = uuid;
		this.contribution = contribution;
	}

	/** a contributor with just UUID */
	public Contributor(UUID uuid) {
		this(uuid, null);
	}

	/** get the contribution */
	public String getContribution() {
		return this.contribution;
	}

	/** check if the contribution is valid */
	public boolean hasContribution() {
		return this.contribution != null;
	}

	/** get the UUID */
	public UUID getUUID() {
		return this.uuid;
	}
}
