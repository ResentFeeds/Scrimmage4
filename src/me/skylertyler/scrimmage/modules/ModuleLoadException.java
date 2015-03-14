package me.skylertyler.scrimmage.modules;

public class ModuleLoadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ModuleLoadException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
