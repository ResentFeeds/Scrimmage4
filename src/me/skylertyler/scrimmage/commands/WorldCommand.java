package me.skylertyler.scrimmage.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class WorldCommand implements CommandExecutor {

	public WorldCommand() {

	} 
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("myworld")) {
				String format = GRAY + "Your current world is " + GREEN
						+ player.getWorld().getName();
				player.sendMessage(format);
			}
		}else{
			sender.sendMessage(RED + "You need to be a player to do this command!");
		}
		return true;
	}

}
