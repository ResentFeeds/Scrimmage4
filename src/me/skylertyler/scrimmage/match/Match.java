package me.skylertyler.scrimmage.match;

import java.util.ArrayList;
import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.config.types.Config;
import me.skylertyler.scrimmage.event.MatchEndEvent;
import me.skylertyler.scrimmage.event.MatchLoadEvent;
import me.skylertyler.scrimmage.event.MatchStartEvent;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapHandler;
import me.skylertyler.scrimmage.scoreboard.Scoreboard;
import me.skylertyler.scrimmage.team.TeamHandler;
import me.skylertyler.scrimmage.timers.GameStartTimer;
import me.skylertyler.scrimmage.utils.ChannelUtils;
import me.skylertyler.scrimmage.utils.MessageUtils;
import me.skylertyler.scrimmage.utils.StringUtils;

import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

public class Match {

	private final int id;
	/** a match id */
	private final Map map;
	/** the current map */
	private MatchState state;
	/** the match state */
	private final Scrimmage scrim;
	/** the main class */
	private final PluginManager pm;
	/** the plugin manager */
	private World world;
	/** the current world for the map */
	private final MapHandler handler;
	/** the map handler field */
	private final TeamHandler thandler;
	/** the team handlers field */
	private Map next = null;
	/** the next map */
	private Scoreboard board;
	private GameStartTimer startTimer;
	private List<Channel> channels;
	private String barMessage;

	/** the message for the bar */

	public Match(Scrimmage scrim, int id, Map map) {
		this.channels = new ArrayList<>();
		this.channels.add(ChannelUtils.newGlobalChannel());
		this.channels.add(ChannelUtils.newTeamChannel());
		this.channels.add(ChannelUtils.newAdminChannel());
		this.scrim = scrim;
		this.id = id;
		this.map = map;
		/**
		 * the message for the bar if enabled!
		 */
		Config config = this.scrim.getConfigFile();
		/** check if the bar message is enabled */
		if (config.barEnabled()) {
			/** the message from the config */
			String message = config.getBarMessage();
			/** new bar Message */
			/*
			 *  #map# will be replaced by the map name 
			 *  $authors$ will be replaced by the all the authors for the in a english compound as coded below 
			 */
			this.barMessage = message.replace("#map#", map.getInfo().getName())
					.replace(
							"$authors$",
							StringUtils.listToEnglishCompound(map.getInfo()
									.getAuthorNames().keySet()));
		}
		this.handler = new MapHandler(this);
		this.handler.loadMap(map, id);
		this.pm = scrim.getServer().getPluginManager();
		// match load event
		loadMatch(this);
		this.thandler = new TeamHandler();
		this.setState(MatchState.Idle);
		this.board = new Scoreboard(getScrimmage().getConfigFile().isScoreboardEnabled());
		runStartGameTimer();
	}

	public void loadMatch(Match match) {
		MatchLoadEvent event = new MatchLoadEvent(match);
		match.getPluginManager().callEvent(event);
	}

	public void runStartGameTimer() {
		this.startTimer = new GameStartTimer(30, false, this);
		this.startTimer.run();
	}

	public boolean isRunning() {
		return this.state == MatchState.Running;
	}

	public boolean isIdle() {
		return this.state == MatchState.Idle;
	}

	public void setState(MatchState state) {
		this.state = state;
	}
  
	public void startMatch() {
		this.setState(MatchState.Running);
		MessageUtils.broadcastStartMessage();
		MatchStartEvent event = new MatchStartEvent(this);
		this.pm.callEvent(event);
	}

	// add a team parameter to check if the match has a current match has a
	// winner
	public void endMatch() {
		this.setState(MatchState.Finished);
		MessageUtils.broadcastFinishedMessage();
		MatchEndEvent event = new MatchEndEvent(this);
		this.pm.callEvent(event);
	}

	public MatchState getState() {
		return this.state;
	}

	public Map getMap() {
		return this.map;
	}

	public PluginManager getPluginManager() {
		return this.pm;
	}

	public Scrimmage getScrimmage() {
		return this.scrim;
	}

	public int getID() {
		return this.id;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public World getWorld() {
		return this.world;
	}

	public TeamHandler getTeamHandler() {
		return this.thandler;
	}

	public MapHandler getMapHandler() {
		return this.handler;
	}

	public boolean hasNext() {
		return this.next != null;
	}

	public void setNext(Map map) {
		this.next = map;
	}

	public Map getNext() {
		return this.next;
	}

	public void broadcast(String string) {
		this.getScrimmage().getServer().broadcastMessage(string);
	}

	public Scoreboard getScoreboard() {
		return this.board;
	}

	public GameStartTimer getStartTimer() {
		return this.startTimer;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public boolean hasBarMessage() {
		return getBarMessage() != null;
	}

	public String getBarMessage() {
		return this.barMessage;
	}

}
