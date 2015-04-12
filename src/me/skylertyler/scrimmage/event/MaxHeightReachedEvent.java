package me.skylertyler.scrimmage.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import me.skylertyler.scrimmage.match.Match;

public class MaxHeightReachedEvent extends MatchEvent {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	/** the player to send the message to */

	private final int height;
	/** the max height */

	private final String message;
	/** the message to send to the player */

	private String plural;

	/** if its plural <-- */

	public MaxHeightReachedEvent(Match match, Player player, int height,
			String message) {
		super(match);
		this.player = player;
		this.height = height;
		this.message = message;
		if (height == 1) {
			this.plural = "";
		} else {
			if (height != 0 && height > 1) {
				this.plural = "s";
			}
		}
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * 
	 * @return the player
	 */

	public Player getPlayer() {
		return player;
	}

	/**
	 * 
	 * @return the max height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return get the message
	 */
	public String getMessage() {
		return message;
	}

	public String getPlural() {
		return plural;
	}

	/**
	 * 
	 * @return if its plural
	 */
	public boolean isPlural() {
		return this.getPlural() != "" ? this.getPlural() == "s" : this
				.getPlural() == "";
	}

}
