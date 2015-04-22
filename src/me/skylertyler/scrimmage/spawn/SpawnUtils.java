package me.skylertyler.scrimmage.spawn;

import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.modules.SpawnModule;
import me.skylertyler.scrimmage.team.Team;
import me.skylertyler.scrimmage.utils.KitUtils;

import org.bukkit.entity.Player;

public class SpawnUtils {

	public static void spawn(Player player) {
		Team team = Scrimmage.getScrimmageInstance().getMatch()
				.getTeamHandler().teamForPlayer(player);
		List<Spawn> spawns = getSpawnModule().getSpawnParser().getSpawns();
		for (Spawn spawn : spawns) {
			if (spawn.getTeam().equals(team)) {
				spawn.spawn(player);
				if (spawn.getKit().isPresent()) {
					KitUtils.applyKit(spawn.getKit().get(), player);
				}
			}
		}
	}

	public static SpawnModule getSpawnModule() {
		return (SpawnModule) Scrimmage.getScrimmageInstance().getLoader()
				.getContainer().getModule(SpawnModule.class);
	}
}
