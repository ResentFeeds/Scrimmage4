package me.skylertyler.scrimmage.utils;

public class NumberUtils {

	public static int parseInteger(String input) {
		int number = Integer.parseInt(input);
		return number;
	}

	public static double parseDouble(String input) {
		double dob = Double.parseDouble(input);
		return dob;
	}

	public static float parseFloat(String input) {
		float flo = Float.parseFloat(input);
		return flo;
	}
}
