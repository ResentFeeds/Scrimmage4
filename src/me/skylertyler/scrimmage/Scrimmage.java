package me.skylertyler.scrimmage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.commands.TestCommand;
import me.skylertyler.scrimmage.exception.MapInfoLoadException;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapLoader;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.modules.AnotherTestModule;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.modules.ModuleFactory;
import me.skylertyler.scrimmage.modules.TestModule;
import me.skylertyler.scrimmage.utils.ConsoleUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.xml.sax.SAXException;

//TODO fix the current JDOMEXCEPTION! 
// TODO to resolve this error lets try to get a newer version of jdom!
public class Scrimmage extends JavaPlugin {

	protected boolean sportBukkit;
	protected Match match;
	protected boolean craftBukkit;
	protected ModuleFactory moduleFactory;

	protected static Scrimmage scrim;
	protected static File rotationFile = new File("rotation");
	// protected File ROTATION_YML = new File(getDataFolder(), "rotation.yml");

	protected List<Map> rotation = new ArrayList<Map>();
	protected MapLoader loader;

	@Override
	public void onEnable() {
		scrim = this;
		loadSportBukkit();
		// try to load the data folder
		if (!getDataFolder().exists()) {
			File file = this.getDataFolder();
			file.mkdir();
		}
		scrim.registerListeners();/*
								 * try { loadRotationMaps(ROTATION_YML); } catch
								 * (IOException e2) { e2.printStackTrace(); }
								 * loadRotation();
								 */
		this.loader = new MapLoader();
		try {
			this.loader.loadMaps();
		} catch (IOException | MapInfoLoadException | SAXException
				| ParserConfigurationException e) {
			e.printStackTrace();
		}

		// switch this current map with the first map in the current rotation!
		setMatch(new Match(getScrimmageInstance(), 1, getLoader()
				.getLoadedMaps().get(0)));
		loadCommands();
		scrim.moduleFactory = new ModuleFactory();
		scrim.registerModules();
		try {
			scrim.moduleFactory.loadModules(getLoader().getXMLDocument());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void loadSportBukkit() {
		if (getScrimmageInstance().hasSportBukkit()) {
			sportBukkit = true;
		} else {
			sportBukkit = false;
		}

		isSportBukkitEnabled(getSportBukkit());

	}

	public void registerCommand(CommandExecutor clazz, String label) {
		getCommand(label).setExecutor(clazz);
	}

	public void loadCommands() {
		registerCommand(new TestCommand(getMatch()), "test");
	}

	public void registerListeners() {
		registerListener(new TestModule());

	}

	public void registerListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	// tells you if sport bukkit is enabled or not!
	public void isSportBukkitEnabled(boolean value) {
		List<String> message = new ArrayList<String>();
		String format = ChatColor.WHITE
				+ "------------------------------------";
		String m = null;
		if (value) {
			m = ChatColor.GREEN + "SportBukkit is " + ChatColor.DARK_GREEN
					+ "Enabled " + ChatColor.GREEN + "on this server";
			message.add(format);
			message.add(m);
			message.add(format);
		} else {
			m = ChatColor.RED + "SportBukkit is " + ChatColor.DARK_RED
					+ "Disabled " + ChatColor.RED + "on this server";
			message.add(format);
			message.add(m);
			message.add(format);
		}

		// add a line to have a space between the message another info
		ConsoleUtils.sendConsoleMessage("");
		for (String line : message) {
			ConsoleUtils.sendConsoleMessage(line);
		}
		ConsoleUtils.sendConsoleMessage("");
	}

	@Override
	public void onDisable() {
		try {
			scrim.moduleFactory.unloadModules();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		scrim = null;
	}

	// TODO make a rotation using a List<String> in a config instead of a
	// rotation file but use rotation folder to load maps! but some are not in
	// the rotation!
	public void loadRotation() {
		if (!hasRotationFile()) {
			getRotationFile().mkdir();
			String message = ChatColor.GRAY + "The rotation file has been "
					+ ChatColor.GREEN + " Created";
			ConsoleUtils.sendConsoleMessage(message);
		}
	}

	public void loadRotationMaps(File file) throws IOException {
		if (file.exists()) {
			FileConfiguration rotconfig = YamlConfiguration
					.loadConfiguration(file);
			String format = null;
			if (rotconfig.get("rotation") != null) {
				List<String> mapNames = rotconfig.getStringList("rotation");
				int size = mapNames.size();
				if (size != 0) {
					if (size != 1) {
						format = "there is " + size + " maps loaded";
					} else {
						format = "there is " + size + " map loaded";
					}
				} else {
					format = "there are no maps loaded!";
				}

			}
			ConsoleUtils.sendConsoleMessage(format);
		} else {
			file.createNewFile();
		}
	}

	public static Scrimmage getScrimmageInstance() {
		return scrim;
	}

	public boolean getSportBukkit() {
		return sportBukkit;
	}

	public boolean hasSportBukkit() {
		return Bukkit.getServer().getName().equalsIgnoreCase("SportBukkit");
	}

	public Match getMatch() {
		return this.match;
	}

	public boolean hasRotationFile() {
		return rotationFile.exists();
	}

	public File getRotationFile() {
		return rotationFile;
	}

	public void registerModules() {
		ModuleFactory factory = this.getModuleFactory();
		factory.registerModule(AnotherTestModule.class);
		factory.registerModule(InfoModule.class);
		factory.registerModule(TestModule.class);
	}

	public MapLoader getLoader() {
		return this.loader;
	}

	public ModuleFactory getModuleFactory() {
		return this.moduleFactory;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
