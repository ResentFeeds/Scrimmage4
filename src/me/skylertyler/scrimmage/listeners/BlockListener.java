package me.skylertyler.scrimmage.listeners;
 
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import me.skylertyler.scrimmage.MatchListener;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.MaxBuildHeightModule;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.team.TeamType;
import me.skylertyler.scrimmage.utils.MessageUtils;
import me.skylertyler.scrimmage.utils.ModuleUtils;
import static org.bukkit.ChatColor.*;

public class BlockListener extends MatchListener {

	private MaxBuildHeightModule max = ModuleUtils.getMaxBuildHeightModule();

	int height;

	public BlockListener() {
		super(Scrimmage.getScrimmageInstance().getMatch());
		this.height = this.max.getMaxHeightParser().getMaxHeight().getHeight();
	}

	@EventHandler
	public void onMaxBuild(BlockPlaceEvent event) {
		Team team = this.getMatch().getTeamHandler()
				.teamForPlayer(event.getPlayer());
		if (team.getType() == TeamType.Participating) {

			String format = null;
			if (event.getBlock().getLocation().getY() >= this.height) {
				event.setCancelled(true);
				
				/** if its not 1 */
				if(this.height != 1) {
					format = "blocks";
				} else {
					/** else if it is one */
					format = "block";
				}

				String result = format;

				MessageUtils.warningMessage(event.getPlayer(), RED + "You have reached the maximum build height " + GRAY + "(" + this.height + " " + result + ")");
			}
		}
	}
}
