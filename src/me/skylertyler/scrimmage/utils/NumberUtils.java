package me.skylertyler.scrimmage.utils;

import java.math.BigDecimal;

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

	 /**
     * Round a decimal to the place param
     *
     * @param value  original decimal
     * @param places num of places to round
     * @return output double
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("the places " + places + " can't be lower than 0");

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}
