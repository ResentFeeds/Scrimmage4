package me.skylertyler.scrimmage.config.types;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.skylertyler.scrimmage.config.ConfigType;
import me.skylertyler.scrimmage.config.CoreConfig;
import me.skylertyler.scrimmage.kit.BookKit;
import me.skylertyler.scrimmage.test.ServerType;
import me.skylertyler.scrimmage.utils.BukkitUtils;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.XMLUtils;

public class Config extends CoreConfig {

	/** the config file */
	private final File CONFIG;
	/** the server type that the server is currently in */
	private ServerType type = null;
	/** a prefix */
	private String prefix = null;
	/** enabling/disabling the bar */
	private boolean bar;
	/** enabling/disabling broadcasting */
	private boolean broadcast;
	/** broadcasting frequency */
	private int frequency;
	/** bar message */
	private String message;
	/** enabling/ disabling the book */
	private boolean book;
	/** observers book */
	private BookKit OBS_BOOK;
	/** the name of the server */
	private String name;
	/** broadcasting prefix */
	private String broadcastPrefix;

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
				if (hasString(configuration, "type")) {
					this.type = getTypeFromString(configuration
							.getString("type"));
				} else {
					configuration.addDefault("type", "running");
				}

				if (hasString(configuration, "name")) {
					this.name = configuration.getString("name");
				} else {
					configuration.addDefault("name", "Scrimmage4");
				}
			} else {
				config.createSection("server");
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
			} else {
				config.createSection("broadcast");
			}

			/** bar configuration section */
			ConfigurationSection bar_section = config
					.getConfigurationSection("bar");
			if (hasConfigurationSection(bar_section)) {
				if (hasString(bar_section, "enabled")) {
					this.bar = bar_section.getBoolean("enabled");
				} else {
					/** DEFAULT: the bar is disabled */
					bar_section.addDefault("enabled", false);
				}

				if (hasString(bar_section, "message")) {
					this.message = BukkitUtils.colorize(bar_section
							.getString("message"));
				} else {
					/** default bar message */
					bar_section.addDefault("message",
							"`5Currently Playing `6#map# `5by $authors$");
				}
			}

			/** the observers book */
			ConfigurationSection observer_book = config
					.getConfigurationSection("observer-book");
			if (hasConfigurationSection(observer_book)) {
				if (hasString(observer_book, "enabled")) {
					this.book = observer_book.getBoolean("enabled");
				} else {
					observer_book.addDefault("enabled", true);
				}
				if (hasString(observer_book, "author")
						&& hasString(observer_book, "title")
						&& hasStringList(observer_book, "pages")
						&& hasString(observer_book, "slot")) {
					this.OBS_BOOK = new BookKit(
							BukkitUtils.colorize(observer_book
									.getString("title")),
							BukkitUtils.colorize(observer_book
									.getString("author")),
							BukkitUtils.colorizeList(observer_book
									.getStringList("pages")),
							observer_book.getInt("slot"));
				} else {
					observer_book.addDefault("title", "'4`lPlease Read");
					observer_book.addDefault("author", "`6SkylerTyler1337");
					observer_book.addDefault("pages",
							Arrays.asList("`1write stuff here!", "`2Okay `r?"));
					observer_book.addDefault("slot", 0);
				}
			} else {
				config.createSection("observer-book");
			}

			ConfigurationSection broadcasting_section = config
					.getConfigurationSection("broadcasting");
			if (hasConfigurationSection(broadcasting_section)) {
				if (hasString(broadcasting_section, "prefix")) {
					String prefix = broadcasting_section.getString("prefix");
					if (prefix.contains("`")) {
						this.broadcastPrefix = BukkitUtils.colorize(prefix);
					} else {
						this.broadcastPrefix = prefix;
					}
				} else {
					broadcasting_section.addDefault("prefix", "Scrimmage4");
				}
			} else {
				config.createSection("broadcasting");
			}

			/**
			 * do this little backwards because i like to use the prefix within
			 * the header
			 */
			// NOTE You can actually change whater ever the prefix is and it
			// will be in the header whenever you reload
			config.options().copyDefaults(true);
			config.options().header("------ " + this.prefix + " Config -----");
			config.options().copyHeader(true);
			config.save(file);
		} else {
			Log.logWarning("the file " + file.getName() + " doesn't exist");
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

	public boolean barEnabled() {
		return this.bar;
	}

	public String getBarMessage() {
		return this.message;
	}

	public boolean bookEnabled() {
		return this.book;
	}

	public BookKit getObserversBook() {
		return this.OBS_BOOK;
	}

	public String getName() {
		return this.name;
	}

	public String getBroadcastPrefix() {
		return this.broadcastPrefix;
	}

	public boolean hasName() {
		return this.getName() != null || this.getName() != "";
	}
}