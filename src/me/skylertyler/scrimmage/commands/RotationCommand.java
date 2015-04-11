package me.skylertyler.scrimmage.commands;

import static org.bukkit.ChatColor.*;

import java.util.List;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.map.Map;
import me.skylertyler.scrimmage.pagination.RotationMapsPage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RotationCommand implements CommandExecutor {

	private final Scrimmage scrim;

	public RotationCommand(Scrimmage scrim) {
		this.scrim = scrim;
	}

	public Scrimmage getScrimmage() {
		return this.scrim;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("rotation")) {
				Player player = (Player) sender;
				List<String> rotNames = Scrimmage.getScrimmageInstance()
						.getRotationConfig().getRotationMapNames();
				new RotationMapsPage<String>() {
				}.display(player, rotNames, 1);
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to do this command!");
		}
		return true;
	}
}
