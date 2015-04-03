package me.skylertyler.scrimmage.timers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.skylertyler.scrimmage.config.types.Config;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchState;

public class GameStartTimer extends CountDownTimer {

	protected Match match;
	private int bTimer;

	public GameStartTimer(int time, boolean cancelled, Match match) {
		super(time, cancelled);
		this.match = match;
	}

	@Override
	public String statusString(int time) {
		String message = ChatColor.GREEN + "Match is starting in "
				+ ChatColor.DARK_RED + time;
		String format = null;
		if (time != 1) {
			format = message + ChatColor.GREEN + " seconds";
		} else {
			format = message + ChatColor.GREEN + " second";
		}
		return format;
	}

	@Override
	public void hasEnded() {
		final Match match = this.getMatch();
		match.startMatch();
		Bukkit.getServer().getScheduler().cancelTask(getCountdown());

		// this will run if the config has the 'broadcast-map' enabled etc.
		Config config = getMatch().getScrimmage().getConfigFile();
		if (config.isEnabled()) {
			this.bTimer = Bukkit
					.getServer()
					.getScheduler()
					.scheduleAsyncRepeatingTask(match.getScrimmage(),
							new Runnable() {
								int time = 1;
								@Override
								public void run() {
									if (time != 0) {
										time--;
									} else {
										match.broadcast(match.getMap().getShortMapDescription());
										Bukkit.getServer().getScheduler()
												.cancelTask(getBroadcastTimer());
									}
								}

							}, 0, 20);
		}
	}

	public Match getMatch() {
		return this.match;
	}

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
