package me.skylertyler.scrimmage.author;

public class Author {

	protected String name;
	protected String contribution;
	protected String uuid;

	public Author(String name, String contribution, String uuid) {
		this.name = name;
		this.contribution = contribution;
		this.uuid = uuid;
	}

	public Author(String name, String uuid) {
		this.name = name;
		this.contribution = null;
		this.uuid = uuid;
	}

	public String getName() {
		return this.name;
	}

	public String getContribution() {
		return this.contribution;
	}

	public String getUUID() {
		return this.uuid;
	}

	public boolean hasContribution() {
		return this.contribution != null;
	}
}
