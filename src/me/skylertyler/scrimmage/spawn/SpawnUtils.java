package me.skylertyler.scrimmage.spawn;

import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.kit.Kit;
import me.skylertyler.scrimmage.modules.SpawnModule;
import me.skylertyler.scrimmage.regions.types.CuboidRegion;
import me.skylertyler.scrimmage.team.Team;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class SpawnUtils {

	public static void spawn(Player player) {
		Team team = Scrimmage.getScrimmageInstance().getMatch()
				.getTeamHandler().teamForPlayer(player);
		List<Spawn> spawns = getSpawnModule().getSpawnParser().getSpawns();
		for (Spawn spawn : spawns) {
			if (spawn.getTeam().equals(team)) {
				if (spawn.getRegion() instanceof CuboidRegion) {
					CuboidRegion region = (CuboidRegion) spawn.getRegion();
					Vector maxVector = region.getMax().getVector();
					Vector minVector = region.getMin().getVector();
					int newX = (int) (maxVector.getX() - minVector.getX() + 1);
					int newY = (int) (maxVector.getY() - minVector.getY() + 1);
					int newZ = (int) (maxVector.getZ() - minVector.getZ() - 1);
					Location loc = new Location(Scrimmage
							.getScrimmageInstance().getMatch().getWorld(),
							newX, newY, newZ);
					player.teleport(loc);
				}

				if (spawn.hasKit()) {
					Kit kit = spawn.getKit();
					kit.applyKit(player);
				}
			}
		}
	}

	public static SpawnModule getSpawnModule() {
		return (SpawnModule) Scrimmage.getScrimmageInstance().getLoader()
				.getContainer().getModule(SpawnModule.class);
	}
}
