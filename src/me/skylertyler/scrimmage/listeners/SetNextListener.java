package me.skylertyler.scrimmage.listeners;

import me.skylertyler.scrimmage.event.MapsAreIdenticalEvent;
import me.skylertyler.scrimmage.event.SetNextEvent;
import me.skylertyler.scrimmage.map.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.ChatColor.*;

public class SetNextListener implements Listener {

	public SetNextListener() {

	}

	@EventHandler
	public void onSetNext(SetNextEvent event) {
		Player player = event.getPlayer();
		Map map = event.getMap();
		String result = RED + player.getName() + DARK_PURPLE
				+ " set the next map to " + GOLD + map.getInfo().getName();
		Bukkit.broadcastMessage(result);
	}

	@EventHandler
	public void onMapExact(MapsAreIdenticalEvent event) {
		Player player = event.getPlayer(); 
		player.sendMessage(event.getMessage());
	}
}
