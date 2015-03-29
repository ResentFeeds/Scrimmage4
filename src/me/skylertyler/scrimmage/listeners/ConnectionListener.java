package me.skylertyler.scrimmage.listeners;

import java.util.Map.Entry;

import me.skylertyler.scrimmage.event.MatchStartEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchState;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.utils.Log;
import static org.bukkit.ChatColor.*;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ConnectionListener implements Listener {

	protected Match match;
	private ChatColor dark_aqua = DARK_AQUA;
	private ChatColor aqua = AQUA;

	public ConnectionListener(Match match) {
		this.match = match; 
	}

	@EventHandler
	public void onServerList(ServerListPingEvent event) {
		String format = null;
		Match match = this.getMatch();
		String prefix = aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=[ ";

		String suffix = aqua + " ]=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=";
		if (match != null) {
			MatchState ms = match.getState();
			if (ms != null) {
				String state = ms.toString();
				format = prefix + state + suffix;
			}
		}
		event.setMotd(format);
	}

	public Match getMatch() {
		return this.match;
	}

	@EventHandler
	public void onMatchStart(MatchStartEvent event) {
		Match match = event.getMatch();
		if (match != null) {
			Log.logWarning(match.getID() + " ");
		}
	}

	// works (for now)
	@EventHandler
			if (region.getValue() instanceof BlockRegion) { 
	public void onBlockPlace(BlockPlaceEvent event) {
		for (Entry<String, Region> region : RegionUtils.getRegions().entrySet()) {
			if (region.getValue() instanceof BlockRegion) {
				BlockRegion bRegion = (BlockRegion) region.getValue();
				if (bRegion != null) {
					Log.logWarning("passed != null checking");
					if (bRegion.containsVector(event.getBlock().getLocation()
							.toVector())) {
						event.setCancelled(true);
						event.getPlayer()
								.sendMessage(
										ChatColor.RED
												+ "You cant place in this region called "
												+ bRegion.getName());
					}
				}
			}
		}
	}
}
