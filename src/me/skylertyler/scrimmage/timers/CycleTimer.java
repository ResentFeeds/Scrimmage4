package me.skylertyler.scrimmage.timers;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchState;

import org.bukkit.Bukkit;

import static org.bukkit.ChatColor.*;

public class CycleTimer extends CountDownTimer {

	private final Match match;

	public CycleTimer(Match match, int time, boolean cancelled) {
		super(time, cancelled);
		this.match = match;
	}

	@Override
	public void run() {
		if (!isCancelled()) {
			Match old = this.getMatch();
			if (old.isRunning()) {
				// TODO add the team paramter to check if the old match has a
				// winner or not !
				old.endMatch();
			}
			if (time != 0) {
				time--;
				old.setState(MatchState.Cycling);
				if (time % 5 == 0 && time > 5 || time <= 5) {
					Bukkit.getServer().broadcastMessage(statusString(time));
				}
			} else {
				this.hasEnded();
			}
		} else {
			this.setCancelled(true);
		}
	}

	@Override
	public String statusString(int time) {
		Map next = getMatch().getNext();
		String format = null;
		String seconds = null;
		if (time != 0) {
			if (time != 1) {
				seconds = "seconds";
			} else {
				seconds = "second";
			}
			format = DARK_AQUA + "Cycling to " + AQUA
					+ next.getInfo().getName() + DARK_AQUA + " in " + DARK_RED
					+ time + DARK_AQUA + " " + seconds;

		} else {
			format = DARK_AQUA + "Cycled to " + AQUA + next.getInfo().getName();
		}

		String result = format;
		return result;
	}

	@Override
	public void hasEnded() {
		Match old = this.getMatch();
		cycleAndMakeMatch(old);
	}

	public void cycleAndMakeMatch(Match old) {
		Scrimmage scrim = Scrimmage.getScrimmageInstance();
		int newID = old.getID() + 1;
		Map newMap = old.getNext();
		scrim.setMatch(new Match(scrim, newID, newMap));
	}

	public Match getMatch() {
		return this.match;
	}
}
