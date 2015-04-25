package me.skylertyler.scrimmage.listeners;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.MatchListener;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.modules.MaxBuildHeightModule;
import me.skylertyler.scrimmage.team.Team; 
import me.skylertyler.scrimmage.utils.ModuleUtils;
import me.skylertyler.scrimmage.utils.TeamUtils;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener extends MatchListener {

	private MaxBuildHeightModule mbhm;
	private int height;
	private String plural;

	public BlockListener() {
		super(Scrimmage.getScrimmageInstance().getMatch());
		this.height = ModuleUtils.getMaxBuildHeightModule()
				.getMaxHeightParser().getMaxHeight().getHeight();
	}

	public boolean check(Player player) {
		for (Team team : TeamUtils.getParticpatingTeams()) {
			if (team.containsPlayer(player)) {
				return true;
			}
		}
		return false;
	}

	// TODO remove MaxHeightReachedEvent
	@EventHandler
	public void onMaxBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		String message = WHITE + "You have reached the maximum build height ";
		Match match = this.getMatch();
		if (check(player) && match.isRunning()) {
			if (block.getY() >= this.height) {
				event.setCancelled(true);
				player.sendMessage(message + GRAY + "(" + this.height
						+ " blocks)");
			}
		}
	} 
	public boolean isPlural() {
		return this.plural == "s";
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		String message = WHITE + "You have reached the maximum build height ";
		Match match = this.getMatch();
		if (check(player)) {
			if (block.getY() >= this.height && match.isRunning()) {
				event.setCancelled(true);
				player.sendMessage(message + GRAY + "(" + this.height
						+ " blocks)");
			}
		}
	}

	public MaxBuildHeightModule getMaxBuildHeight() {
		return this.mbhm;
	}
}
