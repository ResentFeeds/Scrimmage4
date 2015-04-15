package me.skylertyler.scrimmage.listeners;

import java.util.Map.Entry;

import me.skylertyler.scrimmage.event.ScoreboardLoadEvent;
import me.skylertyler.scrimmage.event.MatchLoadEvent;
import me.skylertyler.scrimmage.event.MatchStartEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.match.MatchState;
import me.skylertyler.scrimmage.regions.Region;
import me.skylertyler.scrimmage.regions.RegionUtils;
import me.skylertyler.scrimmage.regions.types.BlockRegion;
import me.skylertyler.scrimmage.regions.types.CuboidRegion;
import me.skylertyler.scrimmage.regions.types.CylinderRegion;
import me.skylertyler.scrimmage.regions.types.SphereRegion;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.Characters;
import me.skylertyler.scrimmage.utils.Log;
import me.skylertyler.scrimmage.utils.TeamUtils;
import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scoreboard.Scoreboard;

public class ConnectionListener implements Listener {

	private final Match match;
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
				String before = Characters.AllowCharacters(Characters.Raquo
						.getUTF()) + " ";
				String after = " "
						+ Characters.AllowCharacters(Characters.Laquo.getUTF());
				String name = match.getMap().getInfo().getName();
				String light_purple = ChatColor.LIGHT_PURPLE + name;
				format = prefix + state + before + light_purple + state + after
						+ suffix;
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

	// just testing the regions below :) // will remove this when done!
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
						event.getPlayer()
								.sendMessage(
										ChatColor.RED
												+ "You cant place in this region called "
												+ bRegion.getName());
					}
				}
			} else if (region.getValue() instanceof CuboidRegion) {
				CuboidRegion cuboid = (CuboidRegion) region.getValue();
				if (cuboid != null) {
					Log.logWarning("passed!");
					if (cuboid.containsVector(event.getBlock().getLocation()
							.toVector())) {
						event.setCancelled(true);
						String format = null;
						String you = "You cant place a block here";
						if (cuboid.hasName()) {
							String name = cuboid.getName();
							format = you + " in the region called " + name;
						} else {
							format = you;
						}

						event.getPlayer().sendMessage(format);
					}
				}
			} else if (region.getValue() instanceof SphereRegion) {
				SphereRegion sphere = (SphereRegion) region.getValue();
				if (sphere != null
						&& sphere.containsVector(event.getBlock().getLocation()
								.toVector())) {
					event.setCancelled(true);
					String format = null;
					String you = "You cant place blocks in ";
					if (sphere.hasName()) {
						format = you + sphere.getName();
					} else {
						format = you + " here";
					}

					String result = format;
					event.getPlayer().sendMessage(result);
				}
			} else if (region.getValue() instanceof CylinderRegion) {
				CylinderRegion cylinder = (CylinderRegion) region.getValue();
				String format = null;
				if (cylinder != null
						&& cylinder.containsVector(event.getBlock()
								.getLocation().toVector())) {
					event.setCancelled(true);
					if (cylinder.hasName()) {
						String name = cylinder.getName();
						format = "You cant place blocks in " + name;
					} else {
						format = "You cant place blocks in here!";
					}

					String result = format;
					event.getPlayer().sendMessage(result);
				}
			}
		}
	}

	// make this load before
	@EventHandler
	public void onMatchLoad(MatchLoadEvent event) {
		Match match = event.getMatch();
		ScoreboardLoadEvent scoreboard_event = new ScoreboardLoadEvent(match,
				match.getScoreboard().getNewBoard());
		match.getPluginManager().callEvent(scoreboard_event);
	}

	// try to fix this error with this!
	@EventHandler
	public void loadBoard(ScoreboardLoadEvent event) {
		Match match = event.getMatch();
		Scoreboard board = event.getBoard();
		if (match != null && board != null) {
			Bukkit.broadcastMessage(match.getID()
					+ " is the id of this match! ");
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Team observers = TeamUtils.getObservers();
		event.setJoinMessage(null);
		if (this.match != null && this.match.isIdle() || this.match.isRunning()) {
			this.match.getTeamHandler()
					.addParticpatingMember(observers, player);
		}
	}
}
