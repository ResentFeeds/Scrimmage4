package me.skylertyler.scrimmage.contributor;

public class Contributor {

	private final String contribution;
	private final String contributor;

	public Contributor(String contribution, String contributor) {
		this.contribution = contribution;
		this.contributor = contributor;
	}

	public Contributor(String contributor) {
		this(null, contributor);
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
}
