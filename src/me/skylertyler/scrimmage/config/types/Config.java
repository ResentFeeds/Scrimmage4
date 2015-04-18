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
	private int frequency;

	public Config(File config) {
		this.CONFIG = config;
	}

	@Override
	public void loadConfig() throws IOException {
		File file = this.getCONFIG();
		if (configExist()) {
			FileConfiguration config = YamlConfiguration
					.loadConfiguration(file);
			/** the server configuration section */
			ConfigurationSection configuration = config
					.getConfigurationSection("server");
			/** the server type */
			if (hasConfigurationSection(configuration)) {
				String type = configuration.getString("type");
				if (type != null) {
					this.type = getTypeFromString(type);
				} else {
					configuration.addDefault("type", "running");
				}

				/** prefix */
				if (hasString(configuration, "prefix")) {
					this.prefix = configuration.getString("prefix");
				} else {
					configuration.addDefault("prefix", "Scrimmage4");
				}

				/** broadcast configuration section */
				ConfigurationSection match_settings = config
						.getConfigurationSection("broadcast");

				if (hasConfigurationSection(match_settings)) {
					/** configurable enabled method default would be false */
					if (hasString(match_settings, "enabled")) {
						String broadcast = match_settings.getString("enabled");
						this.broadcast = XMLUtils.parseBoolean(broadcast);
					} else {
						match_settings.addDefault("enabled", false);
					}

					/** frequency */
					if (hasString(match_settings, "frequency")) {
						this.frequency = match_settings.getInt("frequency");
					} else {
						match_settings.addDefault("frequency", 600);
					}
				}
				
				
				/** bar configuration section */
				ConfigurationSection bar_section = config.getConfigurationSection("bar");
				 if(bar_section != null){
					 
				 } else{
					 /** create the section if it doesnt exist */
					 config.createSection("bar"); 
				 }
			}
			/** do this little backwards because i like to use the prefix within the header */
			// NOTE You can actually change whater ever the prefix is and it will be in the header whenever you reload 
			config.options().copyDefaults(true);
			config.options().header("------ " + this.prefix + " Config -----");
			config.options().copyHeader(true);
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

	public boolean hasFrequency() {
		return this.frequency > 0;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public boolean hasBar() {
		return this.bar == true;
	}

	public boolean broadcastEnabled() {
		return this.broadcast == true;
	}
}
