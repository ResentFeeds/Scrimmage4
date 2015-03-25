package me.skylertyler.scrimmage.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import static org.bukkit.ChatColor.*; 
import me.skylertyler.scrimmage.modules.MaxBuildHeightModule; 

public class BlockListener implements Listener{

	private MaxBuildHeightModule mbhm;

	public BlockListener(MaxBuildHeightModule mbhm) {
		this.mbhm = mbhm;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		// TODO
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Location blockLocation = block.getLocation();
		if (blockLocation.getY() >= getMaxBuildHeight().getMaxHeight()
				.getHeight()) {
			event.setCancelled(true);
			player.sendMessage(WHITE
					+ "You have reached the maximum build height " + GRAY + "("
					+ this.getMaxBuildHeight().getMaxHeight().getHeight()
					+ " blocks)");
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		// TODO
	}

	public MaxBuildHeightModule getMaxBuildHeight() {
		return this.mbhm;
	}
}
