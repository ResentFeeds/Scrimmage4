package me.skylertyler.scrimmage.listeners;

import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.WHITE;
import me.skylertyler.scrimmage.modules.MaxBuildHeightModule;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	private MaxBuildHeightModule mbhm;

	public BlockListener(MaxBuildHeightModule mbhm) {
		this.mbhm = mbhm;
	} 
	//TODO fix this 
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		String message = WHITE + "You have reached the maximum build height ";
		String format = null;
		Location blockLocation = block.getLocation();
		int height = getMaxBuildHeight().getMaxHeight().getHeight();
		if (blockLocation.getY() >= height) {
			event.setCancelled(true);
			String plural = "s";
			if (height == 1) {
				format = GRAY + "(" + height + " block" + ")";
			} else {
				if (height != 0 && height > 1) {
					format = GRAY + "(" + height + " block" + plural + ")";
				}
				String result = message + format;
				player.sendMessage(result);
			}
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
