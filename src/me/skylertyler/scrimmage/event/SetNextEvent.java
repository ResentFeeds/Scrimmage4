package me.skylertyler.scrimmage.event;

import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.match.Match;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class SetNextEvent extends MatchEvent {
	private static final HandlerList handlers = new HandlerList();
	private final Player p;
	private final Map m; 

	public SetNextEvent(Match m, Player player, Map map) {
		super(m);
		this.p = player;
		this.m = map; 
		Match match = this.getMatch();
		// set the next map :)
		match.setNext(map);  
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public Player getPlayer() {
		return this.p;
	}

	public Map getMap() {
		return this.m;
	} 

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
