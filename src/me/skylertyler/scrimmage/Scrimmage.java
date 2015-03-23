package me.skylertyler.scrimmage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.commands.ContributorCommand;
import me.skylertyler.scrimmage.commands.CycleCommand;
import me.skylertyler.scrimmage.commands.JoinCommand;
import me.skylertyler.scrimmage.commands.LocationCommand;
import me.skylertyler.scrimmage.commands.MyTeamCommand;
import me.skylertyler.scrimmage.commands.NextCommand;
import me.skylertyler.scrimmage.commands.RuleCommand;
import me.skylertyler.scrimmage.commands.SetNextCommand;
import me.skylertyler.scrimmage.commands.WorldCommand;
import me.skylertyler.scrimmage.exception.InvalidModuleException;
import me.skylertyler.scrimmage.listeners.ConnectionListener;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapLoader;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchHandler;
import me.skylertyler.scrimmage.modules.InfoModule;
import me.skylertyler.scrimmage.modules.ModuleRegistry;
import me.skylertyler.scrimmage.modules.RegionModule;
import me.skylertyler.scrimmage.modules.TeamModule;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;
import me.skylertyler.scrimmage.utils.ConsoleUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
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

	protected MatchHandler mhandler;

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
		/*                                                 |
		/* put this after the loader loads the maps below  V
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
		setMatchHandler(new MatchHandler(getMatch()));
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
 
	public void loadCommands() {
		registerCommand(new ContributorCommand(getMatch()), "contributors",
				Arrays.asList("contribs"));
		registerCommand(new SetNextCommand(getMatch()), "setnext",
				Arrays.asList("sn", "setnextmap"));
		registerCommand(new NextCommand(getMatch()), "next",
				Arrays.asList("nm", "mn", "nextmap", "mapnext"));
		registerCommand(new MyTeamCommand(getMatch()), "myteam",
				Arrays.asList("mt"));
		registerCommand(new JoinCommand(getMatch()), "join",
				Arrays.asList("j", "jt", "jointeam", "tj"));
		registerCommand(new RuleCommand(getMatch()), "rules",
				Arrays.asList("maprules", "mr"));
		registerCommand(new LocationCommand(), "location", null);
		registerCommand(new WorldCommand(), "myworld", Arrays.asList("mw"));
		registerCommand(new CycleCommand(this), "cycle", null);
	}

	public void registerListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	public void loadListeners() {
		registerListener(new ConnectionListener(getMatch()));
		registerListener(new InfoModule(getMatch().getMap().getInfo()));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("regions")) {
				String format = null;
				for (Entry<String, Region> regions : RegionUtils.getRegions()
						.entrySet()) {
					if (regions.getValue().hasName()) {
						format = regions.getKey()
								+ " " + regions.getValue().getType().toString();
					} else {
						format = regions.getValue().getType().toString();
					}

					player.sendMessage(format);
				}
			} else if (cmd.getName().equalsIgnoreCase("region")) {
				if (args.length == 0 || args.length < 1) {
					player.sendMessage(ChatColor.RED + "Not enough arguments!");
					return false;
				}

				if (args.length > 1) {
					player.sendMessage(ChatColor.RED + "Too many arguments!");
					return false;
				}

				if (args.length == 1) {
					Region region = RegionUtils.getRegionFromString(args[0]);

					if (region == null) {
						player.sendMessage(ChatColor.RED
								+ "There is no region by the name of "
								+ ChatColor.DARK_RED + args[0]);
						return false;
					}

					player.sendMessage(ChatColor.GRAY + "The region is a "
							+ ChatColor.GOLD + region.getType().toString());
				}
			}
		}
		return true;
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
		getMatch().getMapHandler().clearMapsDirectory(
				Bukkit.getWorldContainer());
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
		ModuleRegistry.register(RegionModule.class);
	}

	public MapLoader getLoader() {
		return this.loader;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public MatchHandler getMatchHandler() {
		return mhandler;
	}

	public void setMatchHandler(MatchHandler mhandler) {
		this.mhandler = mhandler;
	}
}
