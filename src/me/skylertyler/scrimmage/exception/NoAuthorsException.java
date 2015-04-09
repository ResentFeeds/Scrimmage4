package me.skylertyler.scrimmage.exception;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.utils.Log;

import com.google.common.base.Preconditions;

public class NoAuthorsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9066533628791728575L;
	private final String message;

	public NoAuthorsException(@Nullable String name) {
		if (Preconditions.checkNotNull(name) != null) {
			this.message = "There are no authors on " + name;
		} else {
			this.message = "There are no authors on a map!";
		}
	}

	public String getMessage() {
		return this.message;
	}

	public void getSpaceMessage() {
		List<String> splits = new ArrayList<>();
		splits.add("=====[ NO AUTHORS ]=====");
		splits.add(getMessage());
		splits.add("========================");
		for (String string : splits) {
			Log.logSevere(string);
		}
	}
}
