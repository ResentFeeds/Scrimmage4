package me.skylertyler.scrimmage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.commands.ContributorCommand;
import me.skylertyler.scrimmage.commands.JoinCommand;
import me.skylertyler.scrimmage.commands.MyTeamCommand;
import me.skylertyler.scrimmage.commands.NextCommand;
import me.skylertyler.scrimmage.commands.RuleCommand;
import me.skylertyler.scrimmage.commands.SetNextCommand; 
import me.skylertyler.scrimmage.exception.InvalidModuleException;
import me.skylertyler.scrimmage.listeners.ConnectionListener;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapLoader;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.modules.ModuleRegistry;
import me.skylertyler.scrimmage.modules.TeamModule;
import me.skylertyler.scrimmage.utils.ConsoleUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.xml.sax.SAXException;

public class Scrimmage extends JavaPlugin {

	// NEED TO implement SpawnModule, and RegionModule to Finish off the teams
	// make overhead color show ! 
	protected boolean sportBukkit;
	protected Match match;

	protected static Scrimmage scrim;
	protected static File rotationFile = new File("rotation");
	// protected File ROTATION_YML = new File(getDataFolder(), "rotation.yml");

	protected List<Map> rotation;
	protected MapLoader loader;

	@Override
	public void onEnable() {
		scrim = this;
		// make the rotation be a new list of maps every time you reload or
		// /restart the server
		this.rotation = new ArrayList<Map>();
		checkSportBukkitEnabled();
		// try to load the data folder
		if (!getDataFolder().exists()) {
			File file = this.getDataFolder();
			file.mkdir();
		}
		/*
		 * try { loadRotationMaps(ROTATION_YML); } catch (IOException e2) {
		 * e2.printStackTrace(); } loadRotation();
		 */

		try {
			loadModules();
		} catch (InvalidModuleException e1) {
			e1.printStackTrace();
		}
		this.loader = new MapLoader(getScrimmageInstance());
		try {
			this.loader.loadMaps();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		// switch the default map with the first map in the current rotation!
		setMatch(new Match(getScrimmageInstance(), 1, getLoader()
				.getLoadedMaps().get(0)));
		loadListeners();
		loadCommands();
	}

	public void checkSportBukkitEnabled() {
		if (getScrimmageInstance().hasSportBukkit()) {
			sportBukkit = true;
		} else {
			sportBukkit = false;
		}

		isSportBukkitEnabled(getSportBukkit());
	}

	public void registerCommand(CommandExecutor clazz, String label,
			List<String> aliases) {
		aliases = new ArrayList<>();
		getCommand(label).setExecutor(clazz);

		for (String aliase : aliases) {
			getCommand(label).getAliases().add(aliase);
		}
	}

	// TODO fix the some commands that have working aliases!
	public void loadCommands() { 
		registerCommand(new ContributorCommand(getMatch()), "contributors", Arrays.asList("contribs"));
		registerCommand(new SetNextCommand(getMatch()), "setnext",
				Arrays.asList("sn", "setnextmap"));
		registerCommand(new NextCommand(getLoader()), "next",
				Arrays.asList("nm", "mn", "nextmap", "mapnext"));
		registerCommand(new MyTeamCommand(getMatch()), "myteam",
				Arrays.asList("mt"));
		registerCommand(new JoinCommand(getMatch()), "join",
				Arrays.asList("j", "jt", "jointeam", "tj"));
		registerCommand(new RuleCommand(getMatch()), "rules", Arrays.asList("maprules","mr"));
	}

	public void loadListeners() {
		registerListener(new ConnectionListener(getMatch()));
		registerListener(new InfoModule(getMatch().getMap().getInfo()));
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
		// unloading the modules when the server is disabled or shut down! by
		// using /stop or it crashes due to some error!
		getLoader().getModuleContainer().unloadModules();
		// make scrim null! (because the server is disabling)
		scrim = null;
	}

	// TODO make a rotation using a List<String> in a config instead of a
	// rotation file but use rotation folder to load maps! but some are not in
	// the rotation!
	public void createRotationFile() {
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

	// modules
	public void loadModules() throws InvalidModuleException {
		ModuleRegistry.register(InfoModule.class);
		ModuleRegistry.register(TeamModule.class);
	}

	public MapLoader getLoader() {
		return this.loader;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
