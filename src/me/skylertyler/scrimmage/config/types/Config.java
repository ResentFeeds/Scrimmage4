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
import me.skylertyler.scrimmage.utils.XMLUtils;

public class Config extends CoreConfig {

	private final File CONFIG;
	private ServerType type = null;
	private String prefix = null;
	private boolean bar;
	private boolean broadcast;

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

				if (hasString(configuration, "prefix")) {
					this.prefix = configuration.getString("prefix");
				} else {
					this.prefix = "Scrimmage4";
				}

				if (hasString(configuration, "bar")) {
					this.bar = XMLUtils.parseBoolean(configuration.getString("bar"));
				} else {
					this.bar = false;
				}

				ConfigurationSection match_settings = config
						.getConfigurationSection("match");

				if (hasConfigurationSection(match_settings)) {
					if (hasString(match_settings, "broadcast-map")) {
						String broadcast = match_settings
								.getString("broadcast-map");
						this.broadcast = XMLUtils.parseBoolean(broadcast);
					} else {
						this.broadcast = false;
					}
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

	public File getCONFIG() {
		return CONFIG;
	}

	public boolean hasPrefix() {
		return this.prefix != null ? true : false;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getFullPrefix() {
		return "[" + this.getPrefix() + "] ";
	}

	public boolean getBar() {
		return this.bar;
	}

	public boolean hasBar() {
		return this.bar == true;
	}

	public boolean isEnabled() {
		return this.broadcast == true;
	}
}
