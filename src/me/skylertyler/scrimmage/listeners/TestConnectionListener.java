package me.skylertyler.scrimmage.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import static org.bukkit.ChatColor.*;

public class TestConnectionListener implements Listener {

	public TestConnectionListener() {

	}

	@EventHandler
	public void onServerList(ServerListPingEvent event) {
		String motd = RED + "Server in development " + GRAY
				+ "please try again later!";
		event.setMotd(motd);
	}
}
