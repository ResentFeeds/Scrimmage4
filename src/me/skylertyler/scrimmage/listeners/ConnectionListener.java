package me.skylertyler.scrimmage.listeners;

import java.util.Map.Entry;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.event.MatchStartEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchState;
import me.skylertyler.scrimmage.modules.RegionModule;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.utils.Log;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ConnectionListener implements Listener {

	protected Match match;

	public ConnectionListener(Match match) {
		this.match = match;
	}

	@EventHandler
	public void onServerList(ServerListPingEvent event) {
		Match match = this.getMatch();

		ChatColor aqua = ChatColor.AQUA;
		ChatColor dark_aqua = ChatColor.DARK_AQUA;
		String prefix = aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=[ ";

		String suffix = aqua + " ]=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=" + dark_aqua + "=" + aqua + "=" + dark_aqua
				+ "=" + aqua + "=";
		String format = null;
		if (match != null) {
			MatchState ms = match.getState();
			if (ms != null) {
				String state = ms.toString();
				format = prefix + state + suffix;
				event.setMotd(format);
			}
		}
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

	//dont know if this works! 
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) { 
		for (Entry<String, Region> region : RegionUtils.getRegions().entrySet()) { 
			if (region.getValue() instanceof BlockRegion) { 
				BlockRegion bRegion = (BlockRegion) region.getValue();
				if (bRegion != null) {
					Log.logWarning("passed != null checking");
					if (bRegion.containsVector(event.getBlock().getLocation()
							.toVector())) {
						event.setCancelled(true);
						event.getPlayer().sendMessage(ChatColor.RED + "You cant place in this region called " + bRegion.getName());
					}
				}
			}
		}
	}
}
