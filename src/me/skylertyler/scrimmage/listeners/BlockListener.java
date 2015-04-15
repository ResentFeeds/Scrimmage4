package me.skylertyler.scrimmage.listeners;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.MatchListener;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.event.MaxHeightReachedEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.modules.MaxBuildHeightModule;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.team.TeamType;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener extends MatchListener {

	private final MaxBuildHeightModule mbhm;

	public BlockListener() {
		super(Scrimmage.getScrimmageInstance().getMatch());
		this.mbhm = (MaxBuildHeightModule) Scrimmage.getScrimmageInstance()
				.getLoader().getContainer()
				.getModule(MaxBuildHeightModule.class);
	}

	public boolean check(Player player) {
		Team team = getMatch().getTeamHandler().teamForPlayer(player);

		if (team.getType() == TeamType.Participating
				&& team.containsPlayer(player)) {
			return true;
		}
		return false;
	}

	@EventHandler
	public void onMaxBuild(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		int height = this.getMaxBuildHeight().getMaxHeightParser()
				.getMaxHeight().getHeight();
		Match match = this.getMatch();
		if (block.getY() >= height && match.isRunning() || match.isIdle()) {
			event.setCancelled(check(player));
			String message = WHITE
					+ "You have reached the maximum build height ";
			MaxHeightReachedEvent reached = new MaxHeightReachedEvent(match,
					player, height, message);
			match.getPluginManager().callEvent(reached);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		// TODO
	}

	@EventHandler
	public void onMaxReached(MaxHeightReachedEvent event) {
		Player player = event.getPlayer();
		int height = event.getHeight();
		boolean plural = event.isPlural();
		String message = event.getMessage();
		String format = null;
		if (plural) {
			format = message + GRAY + "(" + height + " block"
					+ event.getPlural() + ")";
		} else {
			format = message + GRAY + "(" + height + " block" + ")";
		}

		String result = format;
		player.sendMessage(result);
	}

	public MaxBuildHeightModule getMaxBuildHeight() {
		return this.mbhm;
	}
}
