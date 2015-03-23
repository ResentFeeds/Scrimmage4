package me.skylertyler.scrimmage.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		 if(sender instanceof Player){
			 Player player = (Player) sender;
			  if(cmd.getName().equalsIgnoreCase("location")){
				  Location location = player.getLocation();
				  int x, y ,z;
				  x = location.getBlockX();
				  y = location.getBlockY();
				  z = location.getBlockZ();
				  player.sendMessage(ChatColor.GRAY + "Your current location is " + x + " ," + y + " ," + z);
				  
			  }
		 }else{
			 sender.sendMessage(ChatColor.RED + "You need to be a player to do this command!");
		 }
		// TODO Auto-generated method stub
		return false;
	}

}
