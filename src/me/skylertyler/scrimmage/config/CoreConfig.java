package me.skylertyler.scrimmage.config;

import java.io.IOException;
import org.bukkit.configuration.ConfigurationSection;

public abstract class CoreConfig {

	public abstract void loadConfig() throws IOException;

	public boolean hasConfigurationSection(
			ConfigurationSection configurationSection) {
		return configurationSection != null ? true : false;
	}

	public abstract boolean configExist();

	public abstract boolean createFile() throws IOException;

	public abstract ConfigType getType();

	public boolean hasType() {
		return this.getType() != null;
	}
	
	// only if needed!
	public boolean hasStringList(ConfigurationSection section,
			String map) {
	   return section.getStringList(map) != null ? true : false;
	}
}
