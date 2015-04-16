package me.skylertyler.scrimmage.event;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.team.Team;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeamChangeEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private final Player p;
	private Team ot;
	private Team nt;
	private boolean cancelled;
	private String a;

	/**
	 * 
	 * @param player
	 *            to player to remove then add to the new team
	 * @param ot
	 *            the old team
	 * @param nt
	 *            the new team
	 */

	public TeamChangeEvent(Player player, Team ot, @Nullable Team nt,
			@Nullable String args) {
		this.p = player;
		this.ot = ot;
		this.nt = nt;
		this.a = args;
	}

	public String getArgsString() {
		return this.a;
	}

	public boolean hasArgsString() {
		return getArgsString() != null;
	}

	public Player getPlayer() {
		return this.p;
	}

	public Team getOldTeam() {
		return this.ot;
	}

	/** gets the new team */
	public Team getNewTeam() {
		return this.nt;
	}

	/** check if there is a new team for the player to join */
	public boolean hasNewTeam() {
		return getNewTeam() != null;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
