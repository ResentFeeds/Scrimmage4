package me.skylertyler.scrimmage.map;

import java.util.List;

public class MapAuthor {

	protected List<String> authors;

	public MapAuthor(List<String> authors) {
		this.authors = authors;
	}

	public List<String> getAuthors() {
		return this.authors;
	}
}
