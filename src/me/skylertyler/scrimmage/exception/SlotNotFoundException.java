package me.skylertyler.scrimmage.exception;

public class SlotNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SlotNotFoundException(int slot) {
		super("the slot of " + slot + " isnt a valid slot id!");
	}
}
