package me.skylertyler.scrimmage.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;
import me.skylertyler.scrimmage.Scrimmage;
import me.skylertyler.scrimmage.channels.Channel;
import me.skylertyler.scrimmage.event.ChannelChangeEvent;
import me.skylertyler.scrimmage.match.Match;
import me.skylertyler.scrimmage.utils.ChannelUtils;

public class GCommand implements CommandExecutor {
 
	private Match match;

	public GCommand() {
		this.match = Scrimmage.getScrimmageInstance().getMatch();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("g")) {
				if (args.length == 0) {
					Channel channel = ChannelUtils.getChannel(player);
					ChannelChangeEvent event = new ChannelChangeEvent(player,
							channel, ChannelUtils.getGlobalChannel());
					this.match.getPluginManager().callEvent(event);
				}

				if (args.length > 1) {
					player.sendMessage(RED + "Too many arguments!");
					return false;
				}

				if (args.length == 1) {
					// TODO ..

				}
			}
		} else {
			sender.sendMessage(RED
					+ "You need to be a player to join the Global Channel");
		}
		return false;
	}

}
