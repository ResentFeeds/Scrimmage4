package me.skylertyler.scrimmage.exception;

public class MapInfoLoadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String message;

	public MapInfoLoadException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
