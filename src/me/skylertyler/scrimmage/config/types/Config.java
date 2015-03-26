package me.skylertyler.scrimmage.config.types;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.skylertyler.scrimmage.config.ConfigType;
import me.skylertyler.scrimmage.config.CoreConfig;
import me.skylertyler.scrimmage.test.ServerType;
import me.skylertyler.scrimmage.utils.Log;

public class Config extends CoreConfig {

	private final File CONFIG;
	private ServerType type = null;

	public Config(File config) {
		this.CONFIG = config;
	}

	@Override
	public void loadConfig() throws IOException {
		File file = this.getCONFIG();
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

	public ServerType getServerType() {
		return this.type;
	}

	public boolean isRunning() {
		return getServerType() == ServerType.Running;
	}

	public boolean inDevelopment() {
		return getServerType() == ServerType.Development;
	}

	@Override
	public boolean hasConfigurationSection(
			ConfigurationSection configurationSection) {
		return configurationSection != null ? true : false;
	}

	@Override
	public boolean configExist() {
		return getCONFIG().exists();
	}

	@Override
	public boolean createFile() throws IOException {
		return getCONFIG().createNewFile();
	}

	@Override
	public ConfigType getType() {
		return ConfigType.CONFIG;
	}

	@Override
	public boolean hasType() {
		return this.getType() != null ? true : false;
	}

	public File getCONFIG() {
		return CONFIG;
	}

}
