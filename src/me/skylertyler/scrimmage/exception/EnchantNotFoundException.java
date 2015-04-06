package me.skylertyler.scrimmage.exception;

public class EnchantNotFoundException extends Exception {

	/**
	 * 
	 */
	// will throw when a enchantment is null :)
	private static final long serialVersionUID = 3881243934376199409L;

	public EnchantNotFoundException(String enchant) {
		super(enchant + " isn't a enchantment!");
	}
}
