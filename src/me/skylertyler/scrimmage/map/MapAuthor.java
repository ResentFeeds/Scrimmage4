package me.skylertyler.scrimmage.map;

import java.util.ArrayList;
import java.util.List;

public class MapAuthor {

	protected List<String> authors;
	
	public MapAuthor() {
		this.authors = new ArrayList<String>();
	}

	public List<String> getAuthors() {
		return this.authors;
	}
	
	
	public void addAuthor(String input){
		this.authors.add(input);
	}
}
