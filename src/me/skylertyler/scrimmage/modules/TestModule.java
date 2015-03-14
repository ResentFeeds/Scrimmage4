package me.skylertyler.scrimmage.modules;

import me.skylertyler.scrimmage.player.ScrimmagePlayer;
import me.skylertyler.scrimmage.utils.ConsoleUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerJoinEvent;
import org.w3c.dom.Document;

@ModuleDescription(name = "test", depends = {}, description = "test")
public class TestModule extends Module {

	public boolean parent = false;

	@Override
	public void load(Document doc) {
		formatMessage(getParent());
	}

	@Override
	public void unload() {
		HandlerList.unregisterAll(this);
	}

	public boolean getParent() {
		return this.parent;
	}
	
	
	public TestModule() { 
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		//ScrimmagePlayer player = ScrimmagePlayer.getPlayer(event.getPlayer());
		Player player = event.getPlayer();
		player.getPlayer().sendMessage(formatMessage(getParent()));
	}

	public String formatMessage(boolean input) {
		String output = null;
		if (input) {
			output = "true";
		} else {
			output = "false";
		}
		return output;
	}
}
