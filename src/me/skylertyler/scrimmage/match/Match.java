package me.skylertyler.scrimmage.match;

import org.bukkit.plugin.PluginManager;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.timers.GameStartTimer;
import me.skylertyler.scrimmage.utils.MessageUtils;

public class Match {

	protected int id;
	protected Map map;
	protected MatchState state;
	protected Scrimmage scrim;
	protected PluginManager pm;

	public Match(Scrimmage scrim, int id, Map map) {
		this.scrim = scrim;
		this.id = id;
		this.map = map;
		this.pm = scrim.getServer().getPluginManager();
		this.setState(MatchState.Idle);
		GameStartTimer timer = new GameStartTimer(3, false, this);
		timer.run();
	}

	public void setState(MatchState state) {
		this.state = state;
	}

	public void startMatch() {
		this.setState(MatchState.Running);
		MessageUtils.broadcastFinishedMessage();
	}

	// TODO add a team paremeter to tell check if the team has a winner!
	public void endMatch() {
		this.setState(MatchState.Finished);
		MessageUtils.broadcastFinishedMessage();
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
}
