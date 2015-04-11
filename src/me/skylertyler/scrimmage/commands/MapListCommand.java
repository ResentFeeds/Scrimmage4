package me.skylertyler.scrimmage.commands;

import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.pagination.LoadedMapsPage;

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
				List<String> mapNames = Scrimmage.getScrimmageInstance()
						.getLoader().getMapNames();
				new LoadedMapsPage<String>() {

				}.display(player, mapNames, 1);
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to do this command!");
		}
		return false;
	}
}
