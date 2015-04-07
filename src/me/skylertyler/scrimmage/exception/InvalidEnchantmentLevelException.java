package me.skylertyler.scrimmage.exception;

public class InvalidEnchantmentLevelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer level;
	private final String enchantment;
	private String message;

	public InvalidEnchantmentLevelException(Integer level, String enchantment) {
		this.level = level;
		this.enchantment = enchantment;
		if (hasEnchantment()) {
			this.message = getLevel() + " is a invalid level for "
					+ getEnchantment();
		} else {
			this.message = "there is a invalid enchantment level!";
		}
	}

	public Integer getLevel() {
		return level;
	}

	public String getEnchantment() {
		return enchantment;
	}

	public boolean hasEnchantment() {
		return this.getEnchantment() != null;
	}

	public String getMesssage() {
		return this.message;
	}

}
