package me.skylertyler.scrimmage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import me.skylertyler.scrimmage.commands.*;
import me.skylertyler.scrimmage.config.types.Config;
import me.skylertyler.scrimmage.config.types.RotationConfig;
import me.skylertyler.scrimmage.exception.InvalidModuleException;
import me.skylertyler.scrimmage.exception.KitNotFoundExecption;
import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.listeners.*;
import me.skylertyler.scrimmage.map.*;
import me.skylertyler.scrimmage.match.*;
import me.skylertyler.scrimmage.modules.*;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;
import me.skylertyler.scrimmage.rotation.Rotation;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.test.TestLoader;
import me.skylertyler.scrimmage.utils.*;

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

	// Alot more in TODO.txt :)
	// need to fix maploader using all the map.xml files at once! (somewhat
	// better)

	// need to remove List<Map> in the rotation config use the rotation slots :)

	// TODO below -_-

	// NEED TO implement SpawnModule, and RegionModule to Finish off the teams
	// make overhead color show !
	private boolean sportBukkit;
	private Match match;

	private static Scrimmage scrim;
	private static File rotationFile = new File("rotation");

	private Rotation rotation;
	private MapLoader loader;

	private MatchHandler mhandler;
	private Config config;
	private RotationConfig ROT_CONFIG;

	@Override
	public void onEnable() {
		scrim = this;
		// try to load the data folder
		if (!getDataFolder().exists()) {
			File file = this.getDataFolder();
			file.mkdir();
		}

		checkSportBukkitEnabled();
		try {
			loadConfig(new File(this.getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/** check if the server type is running */
		if (getConfigFile().isRunning()) {
			try {
				loadLoadedMaps();
				loadRotation(new File(this.getDataFolder(), "rotation.yml"));
				int size = getRotationConfig().getRotMaps().size();
				if (size == 0) {
					/** add a line with nothing to give a space */
					Log.logWarning("");
					Log.logWarning("===================================================");
					Log.logWarning("Scrimmage4 needs at least one map in order to run!");
					Log.logWarning("===================================================");
					/** add a line with nothing to give a space */
					Log.logWarning("");
					/** shutdown the server if there are no maps loaded */
					getServer().shutdown();
					return;
				}
				loadMatch(getRotationConfig().getRotMaps().get(0));
			} catch (SAXException | IOException | ParserConfigurationException e) {
				e.printStackTrace();
			}
		}

		// check if servertype is in development stage
		if (getConfigFile().inDevelopment()) {
			TestLoader testloader = new TestLoader();
			try {
				testloader.loadMaps();

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}

		loadCommands();
		loadListeners();
		Log.logInfo(getConfigFile().getFullPrefix() + " has been Enabled!");
	}

	/**
	 * 
	 * @param input
	 *            a rotation map name
	 * @return
	 */
	public Map getRotationMap(String input) {
		return this.getRotationConfig().getMap(input);
	}

	public void loadRotation(File rotation) throws IOException {
		this.rotation = new Rotation();
		this.ROT_CONFIG = new RotationConfig(rotation);
		boolean exist = this.getRotationConfig().configExist();
		if (exist) {
			this.getRotationConfig().loadConfig();
		} else {
			Log.logWarning(getConfigFile().getFullPrefix()
					+ " Try to /reload again for a rotation.yml!");
			this.getRotationConfig().createFile();
		}
	}

	public RotationConfig getRotationConfig() {
		return this.ROT_CONFIG;
	}

	public void loadLoadedMaps() {
		try {
			loadModules();
		} catch (InvalidModuleException e1) {
			e1.printStackTrace();
		}
		this.loader = new MapLoader(this);
		this.loader.loadMaps();
	}

	public void loadConfig(File file) throws IOException {
		this.config = new Config(file);
		if (this.config.configExist()) {
			this.config.loadConfig();
		} else {
			this.config.createFile();
		}
	}

	public void loadMatch(Map map) throws SAXException, IOException,
			ParserConfigurationException {
		setMatch(new Match(getScrimmageInstance(), 1, map));
		setMatchHandler(new MatchHandler(getMatch()));
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
		registerCommand(new MapListCommand(), "maplist", null);
		registerCommand(new RotationCommand(this), "rotation", null);
		registerCommand(new BroadcastCommand(this), "broadcast",
				Arrays.asList("bc", "bmessage"));
		registerCommand(new AuthorCommand(getMatch()), "authors", null);
		registerCommand(new TCommand(), "t", null);
		registerCommand(new GCommand(), "g", null);
		registerCommand(new ACommand(), "a", null);
		registerCommand(new MyChannelCommand(), "mychannel",
				Arrays.asList("myc", "mc"));
	}

	public void registerListener(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	public void loadListeners() {
		if (getConfigFile().isRunning()) {
			registerListener(new ConnectionListener());
			registerListener(new InfoModule(getMatch().getMap().getInfo()));
			registerListener(new BlockListener());
			registerListener(new SetNextListener());
			registerListener(new ObserverListener());
			registerListener(new ChatListener());
			registerListener(new ChannelListener());
			registerListener(new TeamListener());
		}

		/** if the servertype is in development register this listener below */
		if (getConfigFile().inDevelopment()) {
			registerListener(new TestConnectionListener());
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("regions")) {
				String format = null;

				if (args.length > 0) {
					format = ChatColor.RED + "Too many arguments!";
					player.sendMessage(format);
					return false;
				}

				if (args.length == 0) {
					for (Entry<String, Region> regions : RegionUtils
							.getRegions().entrySet()) {
						Region region = regions.getValue();
						if (region != null) {
							if (region.hasName()) {
								format = regions.getKey() + " "
										+ region.getType().toString();
							} else {
								format = region.getType().toString();
							}
						} else {
							format = ChatColor.RED + "No regions!";
						}
						player.sendMessage(format);
					}
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
			} else if (cmd.getName().equalsIgnoreCase("participating")) {
				if (args.length == 0 || args.length < 1) {
					player.sendMessage(ChatColor.RED + "Not enough arguments!");
					player.sendMessage(ChatColor.RED + "/participating <team>");
					return false;
				}

				if (args.length > 1) {
					player.sendMessage(ChatColor.RED + "Too many arguments!");
					player.sendMessage(ChatColor.RED + "/participating <team>");
					return false;
				}

				if (args.length == 1) {
					Team team = TeamUtils.getParticipatingTeamByID(args[0]);

					if (team == null) {
						player.sendMessage(ChatColor.RED
								+ "there is no participating team by the id of "
								+ ChatColor.DARK_RED + args[0] + ChatColor.RED
								+ "!");
						return false;
					}

					player.sendMessage(ChatColor.WHITE
							+ "the team with that id is " + team.getColor()
							+ team.getName());

				}
			} else if (cmd.getName().equalsIgnoreCase("kit")) {
				if (args.length == 0) {
					for (Kit kit : KitUtils.getKitModule().getKitParser()
							.getKits()) {
						String name = kit.getName();
						player.sendMessage(name + " is a kit!");
					}
				}

				if (args.length > 1) {
					player.sendMessage(ChatColor.RED + "Too many arguments!");
					return false;
				}

				if (args.length == 1) {
					Kit kit = KitUtils.getKitByName(args[0]);

					if (kit == null) {
						try {
							throw new KitNotFoundExecption(args[0], player);
						} catch (KitNotFoundExecption e) {
							// noting
						}
						return false;
					}

					kit.applyKit(player);
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
		if (getConfigFile().isRunning()) {
			getMatch().getMap().getInfo().getAuthorNames().clear();
			FileUtils.clean();
			// unloading the modules when the server is disabled or shut down!
			// by
			// using /stop or it crashes due to some error!
			getLoader().getContainer().unloadModules();
			// make scrim null! (because the server is disabling)
		}
		Log.logInfo(getConfigFile().getFullPrefix() + " has been Disabled!");
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

	/** register modules here */
	public void loadModules() throws InvalidModuleException {
		ModuleRegistry.register(InfoModule.class);
		ModuleRegistry.register(TeamModule.class);
		ModuleRegistry.register(RegionModule.class);
		ModuleRegistry.register(MaxBuildHeightModule.class);
		ModuleRegistry.register(SpawnModule.class);
		ModuleRegistry.register(KitModule.class);
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

	public Rotation getRotation() {
		return rotation;
	}

	public MatchHandler getMhandler() {
		return mhandler;
	}

	public Config getConfigFile() {
		return this.config;
	}
}
