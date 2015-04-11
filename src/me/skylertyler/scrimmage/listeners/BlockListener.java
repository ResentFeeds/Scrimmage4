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

public class BlockListener implements Listener {

	private MaxBuildHeightModule mbhm;

	public BlockListener(MaxBuildHeightModule mbhm) {
		this.mbhm = mbhm;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		// TODO add checking if the player is on a team with a team type of particpating!
		Player player = event.getPlayer(); 
		Block block = event.getBlock();
		String b = null;
		String message = WHITE + "You have reached the maximum build height ";
		String format = null;
		Location blockLocation = block.getLocation();
		int height = getMaxBuildHeight().getMaxHeight().getHeight(); 
			if (blockLocation.getY() >= height) {
				event.setCancelled(true);
				if (height == 1) {
					b = "";
					format = GRAY + "(" + height + " block" + b + ")";
				} else {
					if (height != 0 && height > 1) {
						b = "s";
						format = GRAY + "(" + height + " block" + b + ")";
					}
				}

				String result = message + format;
				player.sendMessage(result);
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
