package me.skylertyler.scrimmage.exception;

public class NoSpawnsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7503171498188233621L;

	public NoSpawnsException(int size) {
		super("there are " + size + " spawns!");
	}
}
