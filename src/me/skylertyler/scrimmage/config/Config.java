package me.skylertyler.scrimmage.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.skylertyler.scrimmage.test.ServerType;
import me.skylertyler.scrimmage.utils.Log;

public class Config {

	private File CONFIG = null;
	private ServerType type = null;

	public Config(File config) {
		this.CONFIG = config;
	}

	public void loadConfig() throws IOException {
		File file = this.getConfig();
		if (configExist()) {
			FileConfiguration config = YamlConfiguration
					.loadConfiguration(file);
			ConfigurationSection configuration = config
					.getConfigurationSection("server");
			if (hasConfigurationSection(configuration)) {
				String type = configuration.getString("type");
				if (type != null) {
					this.type = getTypeFromString(type);
				} else {
					this.type = ServerType.Running;
				}
			}
			config.save(file);
		} else {
			Log.logWarning(file.getName() + " does not exist!");
		}
	}

	private ServerType getTypeFromString(String string) {
		ServerType type = null;
		for (ServerType types : ServerType.values()) {
			if (types.name().replace(" ", "_").equalsIgnoreCase(string)) {
				type = types;
			}
		}
		return type;
	}

	private boolean hasConfigurationSection(
			ConfigurationSection configurationSection) {
		return configurationSection != null ? true : false;
	}

	public boolean configExist() {
		return getConfig().exists() ? true : false;
	}

	public File getConfig() {
		return this.CONFIG;
	}

	public boolean createFile(File file) throws IOException {
		return file.createNewFile();
	}

	public ServerType getType() {
		return this.type;
	}

	public boolean hasType() {
		return getType() != null ? true : false;
	}

	public boolean isRunning() {
		return getType() == ServerType.Running;
	}

	public boolean inDevelopment() {
		return getType() == ServerType.Development;
	}
}
