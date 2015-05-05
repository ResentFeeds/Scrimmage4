package me.skylertyler.scrimmage.map;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.generator.NullChunkGenerator;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.utils.FileUtils;
import me.skylertyler.scrimmage.utils.Log;

public class MapHandler {

	protected Match match;

	public MapHandler(Match match) {
		this.match = match;
	}

	/**
	 * 
	 * @param map
	 *            gets the map you want
	 * @param id
	 *            add +1 every time you load a map!
	 */

	public void loadMap(Map map, int id) {
		try {
			File mapFolder = Scrimmage.getScrimmageInstance().getRotationFile();
			File src = new File(mapFolder.getName(), map.getInfo().getName());
			File dest = new File(src.getParentFile().getParent(), "match-"
					+ src.getName());
			FileUtils.copyFolder(src, dest);
			String matchName = "match-" + src.getName();
			new File(matchName + File.separator + "level.dat").delete();
			Match match = this.getMatch();
			WorldCreator creator = WorldCreator.name(dest.getName());
			creator.generator(new NullChunkGenerator());
			World world = creator.createWorld();
			// try this
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.teleport(world.getSpawnLocation());
				if (players != null) {
					/*
					 * match.getTeamHandler().addPlayerToTeam( match, team,
					 * players);
					 */
				}
			} 
			world.setSpawnFlags(false, false);
			world.setMonsterSpawnLimit(0);
			world.setAutoSave(false);
			match.setWorld(world);
		} catch (IOException e) {
			Log.getLogger().info(
					Scrimmage.getScrimmageInstance().getConfigFile()
							.getFullPrefix()
							+ " Error while loading map: "
							+ map.getInfo().getName());
			e.printStackTrace();
		}

	}

	public Match getMatch() {
		return this.match;
	}

	public void clearMapsDirectory(File directory) {
		if (directory.isDirectory()) {
			for (File children : directory.listFiles()) {
				if (children != null) {
					if (children.isDirectory()
							&& children.getName().startsWith("match-")) {
						children.delete();
					}
				}
			}
		}
	}
}
