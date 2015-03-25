package me.skylertyler.scrimmage.utils;

public class InfinityUtils {

	// getting positive infinity / negative infinity from a string !
	public static Double parseDouble(String input) {
		Double doub = null;
		if (input.equals("oo")) {
			doub = Double.POSITIVE_INFINITY;
		} else if (input.equals("-oo")) {
			doub = Double.NEGATIVE_INFINITY;
		} 
		return doub;
	}
}
