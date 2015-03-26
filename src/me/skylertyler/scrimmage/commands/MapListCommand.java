package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class MapListCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("maplist")) {
				String format = null;
				int i = 1;
				for (Map map : Scrimmage.getScrimmageInstance().getLoader()
						.getLoadedMaps()) {
					if (map != null) {
						format = i + ") " + map.getInfo().getName() + " "
								+ map.getInfo().getVersion();
						i++;
						player.sendMessage(format);
					} else {
						player.sendMessage(RED + "No maps loaded!");
					}
				} 
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to do this command!");
		}
		return false;
	}
}
