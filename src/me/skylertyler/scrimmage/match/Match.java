package me.skylertyler.scrimmage.match;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.event.MatchEndEvent;
import me.skylertyler.scrimmage.event.MatchLoadEvent;
import me.skylertyler.scrimmage.event.MatchStartEvent;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.map.MapHandler;
import me.skylertyler.scrimmage.team.TeamHandler;
import me.skylertyler.scrimmage.timers.GameStartTimer;
import me.skylertyler.scrimmage.utils.MessageUtils;

import org.bukkit.World;
import org.bukkit.plugin.PluginManager;

public class Match {

	protected int id;
	protected Map map;
	protected MatchState state;
	protected Scrimmage scrim;
	protected PluginManager pm;
	protected World world;
	protected MapHandler handler;
	protected TeamHandler thandler;
	private Map next = null;

	public Match(Scrimmage scrim, int id, Map map) {
		this.scrim = scrim;
		this.id = id;
		this.map = map;  
		this.handler = new MapHandler(this);
		System.out.println(getMap().getInfo().getName() + " is this map ");
		this.pm = scrim.getServer().getPluginManager();
		this.thandler = new TeamHandler();
		// calling match start event
		this.setState(MatchState.Idle);
		MatchLoadEvent event = new MatchLoadEvent(this);
		this.pm.callEvent(event);
		// run the start game timer
		runStartGameTimer();
	}

	public void runStartGameTimer() {
		GameStartTimer start = new GameStartTimer(30, false, this);
		start.run();
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
}
