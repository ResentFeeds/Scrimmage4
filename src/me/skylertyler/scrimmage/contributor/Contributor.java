package me.skylertyler.scrimmage.contributor;

public class Contributor {

	protected String contribution;
	protected String contributor;

	public Contributor(String contribution, String contributor) {
		this.contribution = contribution;
		this.contributor = contributor;
	}

	public Contributor(String contributor) {
		this.contributor = contributor;
		this.contribution = null;
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
