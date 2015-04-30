package me.skylertyler.scrimmage.utils;

import net.minecraft.server.v1_8_R1.SharedConstants;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public enum Characters {

	Raquo("\u00BB"), Laquo("\u00AB"), Cross("\u2715"), Check("\u2714"), Wool(
			"\u2610"), DIAMX("\u2756"),ERROR("\u26A0 ");

	private String utf;

	Characters(String utf) {
		this.utf = utf;
	}

	public static String AllowCharacters(String allow) {
		try {
			Field allowed = SharedConstants.class
					.getDeclaredField("allowedCharacters");
			allowed.setAccessible(true);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(allowed, allowed.getModifiers()
					& ~Modifier.FINAL);
			String oldallowedchars = (String) allowed.get(null);
			StringBuilder sb = new StringBuilder();
			sb.append(oldallowedchars);
			sb.append(allow);
			allowed.set(null, sb.toString());
		} catch (Exception ex) {
			//
		}
		return allow;
	}

	public String getUTF() {
		return this.utf;
	}
}
