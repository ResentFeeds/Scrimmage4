package me.skylertyler.scrimmage.commands;

import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.timers.CycleTimer;
import me.skylertyler.scrimmage.utils.NumberUtils;
 
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;
public class CycleCommand implements CommandExecutor {

	private final Scrimmage scrim;
	private CycleTimer timer;

	public CycleCommand(Scrimmage scrim) {
		this.scrim = scrim;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("cycle")) {
				Match match = getScrimmage().getMatch();
				if (match != null) { 
					 if(match.hasNext()){
						 timer = null;
					 	 if(args.length == 0){
					 		 timer = new CycleTimer(match, 15, false);
					 		 return false;
					 	 }
					 	 
					 	 
					 	 if(args.length > 1){
					 		 player.sendMessage(RED + "Too many arguments");
					 		 return false;
					 	 }
					 	 
					 	 if(args.length == 1){
					 		int time = NumberUtils.parseInteger(args[0]);
					 		timer = new CycleTimer(match, time, false);
					 		 return false;
					 	 }  
					 	 
					 	 
					 	 timer.run();
					 }else{
						 //send them a message
						 String format = WHITE + "You need to do " + DARK_RED + "/setnext <map> " + WHITE + " first";
						 player.sendMessage(format);
					 }
				}
			}
		}else{
			sender.sendMessage(RED + "You need to be a player to cycle to the next match!");
		}
		return true;
	}

	public Scrimmage getScrimmage() {
		return this.scrim;
	}
}
