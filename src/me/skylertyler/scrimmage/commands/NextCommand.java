package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.map.MapLoader;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NextCommand implements CommandExecutor {

	protected MapLoader loader;

	public NextCommand(MapLoader loader) {
		this.loader = loader;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (label.equalsIgnoreCase("next")) {
				MapLoader loader = this.getLoader();
				if (loader.hasNext()) {
					player.sendMessage(ChatColor.GRAY + "The next map is "
							+ ChatColor.GOLD
							+ loader.getNext().getInfo().getName());
				} else {
					player.sendMessage(ChatColor.RED
							+ "There is no map set to be next");
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You need to be a player!");
		}
		return false;
	}

	public MapLoader getLoader() {
		return this.loader;
	}

}
