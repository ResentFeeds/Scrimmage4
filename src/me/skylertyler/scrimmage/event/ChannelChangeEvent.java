package me.skylertyler.scrimmage.event;

import javax.annotation.Nullable;

import me.skylertyler.scrimmage.channels.Channel;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class ChannelChangeEvent extends ChannelEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private final Player p;
	private final Channel oc;
	private final Channel nc;
	private String message;
	private boolean cancelled;

	public ChannelChangeEvent(Player player, @Nullable Channel oldchannel,
			Channel newchannel) {
		super();
		this.p = player;
		this.oc = oldchannel;
		this.nc = newchannel;
	}

	public boolean hasOldChannel() {
		return this.getOldChannel() != null;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	public Player getPlayer() {
		return this.p;
	}

	public Channel getOldChannel() {
		return this.oc;
	}

	public Channel getNewChannel() {
		return this.nc;
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
