package me.skylertyler.scrimmage.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.skylertyler.scrimmage.config.types.Config;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchState;

public class GameStartTimer extends CountDownTimer {

	protected Match match;
	private int bTimer;
	private int bTime = 5;

	public GameStartTimer(int time, boolean cancelled, Match match) {
		super(time, cancelled);
		this.match = match;
	}

	// broadcast the message below
	@Override
	public String statusString(int time) {
		String message = ChatColor.GREEN + "Match starting in "
				+ ChatColor.DARK_RED + time;
		String format = null;
		if (time != 1) {
			format = message + ChatColor.GREEN + " seconds";
		} else {
			format = message + ChatColor.GREEN + " second";
		}
		return format;
	}

	@SuppressWarnings("deprecation")
	// call this method when the timer ends :)
	@Override
	public void hasEnded() {
		final Match match = this.getMatch();
		match.startMatch();
		Bukkit.getServer().getScheduler().cancelTask(getCountdown());

		// this will run if the config has the 'broadcast.enabled' is true etc.
		Config config = getMatch().getScrimmage().getConfigFile();
		if (config.broadcastEnabled()) {
			this.bTimer = Bukkit
					.getServer()
					.getScheduler()
					.scheduleAsyncRepeatingTask(match.getScrimmage(),
							new Runnable() {
								@Override
								public void run() {
									if (bTime != 0) {
										bTime--;
									} else {
										Bukkit.getServer()
												.getScheduler()
												.cancelTask(getBroadcastTimer());
										match.broadcast(match.getMap()
												.getShortMapDescription());
									}
								}

								/**
								 * the frequency times the amount of ticks in a
								 * second
								 */
								 
								/** EXAMPLE:
								 *  if the frequency is 2 it would equal 2 * 20 would equal 2 seconds 
								 */
							}, 0, config.getFrequency() * 20);
		}
	}

	public Match getMatch() {
		return this.match;
	}

	// start the timer!
	@Override
	public void run() {
		if (!(isCancelled())) {
			Match match = this.getMatch();
			if (time != 0) {
				time--;
				match.setState(MatchState.Starting);
				if (time % 5 == 0 && time > 5 || time <= 5 && time != 0) {
					Bukkit.getServer().broadcastMessage(statusString(time));
				}
			} else {
				this.hasEnded();
			}
		} else {
			this.setCancelled(true);
		}
	}

	public int getBroadcastTimer() {
		return this.bTimer;
	}
}
