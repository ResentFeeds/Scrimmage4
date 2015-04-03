package me.skylertyler.scrimmage.timers;

import me.skylertyler.scrimmage.Scrimmage;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;

public abstract class CountDownTimer implements Runnable, Cancellable {

	protected boolean cancelled; /* checks if its cancelled or not */
	protected int time; /* amount of time in seconds */
	protected int timer;/* a scheduler */

	@SuppressWarnings("deprecation")
	public CountDownTimer(int time, boolean cancelled) {
		this.time = time + 1;
		this.cancelled = cancelled;
		this.timer = Bukkit.getScheduler().scheduleAsyncRepeatingTask(
				Scrimmage.getScrimmageInstance(), this, 0, 20);
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;

	}

	public abstract void run();

	public String statusString(int time) {
		return "";
	}

	public abstract void hasEnded();

	public int getTimer() {
		return this.time;
	}

	public int getCountdown() {
		return this.timer;
	}

	public boolean isRunning() {
		return isCancelled() == true;
	}
}
