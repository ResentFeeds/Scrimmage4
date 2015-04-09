package me.skylertyler.scrimmage.utils;

import java.util.Collection;
import java.util.Date;

public class StringUtils {
	/**
	 * Shorthand for listToEnglishCompound(list, "", "").
	 * 
	 * @see #listToEnglishCompound(java.util.Collection, String, String)
	 */
	public static final String listToEnglishCompound(Collection<String> list) {
		return listToEnglishCompound(list, "", "");
	}

	/**
	 * Converts a list of strings to a nice English list as a string.
	 * <p/>
	 * For example: In: ["Anxuiz", "MonsieurApple", "Plastix"] Out:
	 * "Anxuiz, MonsieurApple and Plastix"
	 * 
	 * @param list
	 *            List of strings to concatenate.
	 * @param prefix
	 *            Prefix to add before each element in the resulting string.
	 * @param suffix
	 *            Suffix to add after each element in the resulting string.
	 * @return String version of the list of strings.
	 */
	public static String listToEnglishCompound(Collection<?> list,
			String prefix, String suffix) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (Object str : list) {
			if (i != 0) {
				if (i == list.size() - 1) {
					builder.append(" and ");
				} else {
					builder.append(", ");
				}
			}
			builder.append(prefix).append(str).append(suffix);
			i++;
		}
		return builder.toString();
	}

	public static <T> T bestFuzzyMatch(String search, Collection<T> options,
			double threshold) {
		T bestObj = null;
		double bestScore = 0.0;
		for (T obj : options) {
			double score = LiquidMetal.score(obj.toString(), search);
			if (score > bestScore) {
				bestObj = obj;
				bestScore = score;
			} else if (score == bestScore) {
				bestObj = null;
			}
		}
		return bestScore < threshold ? null : bestObj;
	}

	/**
	 * Sanitizes the provided message, removing any non-alphanumeric characters
	 * and swapping spaces with the specified string.
	 * <p/>
	 * Examples: sanitize("Hello! :) How are you?", '-') -->
	 * "Hello--How-are-you" sanitize("I am great, thank you!", '*') -->
	 * "I*am*great*thank*you"
	 * 
	 * @param string
	 *            The message to be sanitized.
	 * @param spaceReplace
	 *            The string to be substituted for spaces.
	 * @return The sanitized string.
	 */
	public static String sanitize(String string, String spaceReplace) {
		return string.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s+",
				spaceReplace);
	}

	public static String _ago(Date when) {
		int tm = (int) (when.getTime() / 1000);
		int cur_tm = (int) (new Date().getTime() / 1000);
		int diff = cur_tm - tm;
		System.out.println(diff);

		String[] pds = { "second", "minute", "hour", "day", "week", "month",
				"year", "decade" };

		int[] lngh = { 1, 60, 3600, 86400, 604800, 2630880, 31570560, 315705600 };

		int v;
		double no = 0;

		for (v = lngh.length - 1; (v >= 0) && ((no = diff / lngh[v]) <= 1); v--) {
			if (v < 0) {
				v = 0;
			}

			tm = cur_tm - (diff % lngh[v]);
		}

		no = Math.floor(no);

		if (no != 1) {
			pds[v] += "s";
		}

		return String.valueOf(no) + " " + pds[v] + " ";
	}
}
